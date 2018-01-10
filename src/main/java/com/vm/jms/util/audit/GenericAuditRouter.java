package com.vm.jms.util.audit;

import com.vm.jms.domain.audit.OrderAudit;
import com.vm.jms.service.audit.AuditService;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.language.simple.SimpleLanguage;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.util.InetAddressUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@CommonsLog
public class GenericAuditRouter extends RouteBuilder {

    private static int redeliveryCount = 0;

    @Value("${service_bus.consumerQueue}")
    private String consumerQueue;

    @Value("${service_bus.deadLetterQueue}")
    private String deadLetterQueue;

    @Autowired
    private AuditService auditService;

    @Autowired
    private EventListenerForAction eventListenerForAction;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void configure() throws Exception {

        log.info("Start of RouteBuilder configure() method.");
        onException(RuntimeException.class).handled(true).maximumRedeliveries(3).onRedelivery(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                redeliveryCount = exchange.getIn().getHeader(Exchange.REDELIVERY_COUNTER, Integer.class);
            }
        }).useOriginalMessage().process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                Exception exception = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
                exchange.getIn().getHeader("exceptionClass", exception.getClass().getSimpleName());
                exchange.getIn().getHeader("failedRouteId", (String)exchange.getProperty(Exchange.FAILURE_ROUTE_ID));
                exchange.getIn().getHeader("failedEndpointUri", (String) exchange.getProperty(Exchange.FAILURE_ENDPOINT));
                exchange.getIn().getHeader("originalEndpoin", exchange.getFromEndpoint().getEndpointKey());
                exchange.getIn().getHeader("machineName", InetAddressUtil.getLocalHostName());
                exchange.getIn().getHeader("originalPayload", (String)exchange.getUnitOfWork().getOriginalInMessage().getBody());
                exchange.getIn().getHeader("redeliveryCount", redeliveryCount);
                Expression exp = SimpleLanguage.simple("${date:now:dd-MM-yyyy}");
                Object dateString = exp.evaluate(exchange, Object.class);
                exchange.getIn().setBody((String) exchange.getUnitOfWork().getOriginalInMessage().getBody());
                log.info("currentTimestamp: "+exchange.getIn().getHeader("currentTimestamp"));
            }
        }).to("amqp:queue", deadLetterQueue);
        log.info("amqp:queue"+ consumerQueue);
        from("amqp:queue"+ consumerQueue).routeId("AuditAPIRoute")
                .log("Received message is ${body}").choice()
                .when(header("EntityName").isEqualTo("EntityName")).unmarshal()
                .json(JsonLibrary.Jackson, OrderAudit.class)
                .bean(eventListenerForAction, "setAuditAction")
                .bean(entityManager, "persist").end();
        log.info("End of RouteBuilder configure() method.");
    }
}
