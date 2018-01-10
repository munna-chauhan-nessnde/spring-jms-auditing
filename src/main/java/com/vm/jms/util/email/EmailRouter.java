package com.vm.jms.util.email;

import com.rabbitmq.client.AMQP;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.util.TimeUtils;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.TimeUnit;

public class EmailRouter extends RouteBuilder {

    @Value("${service_bus.consumerQueue}")
    private String consumerQueue;

    @Value("${service_bus.deadLetterQueue}")
    private String deadLetterQueue;

    @Override
    public void configure() throws Exception {
        errorHandler(deadLetterChannel("amqp:queue" + deadLetterQueue)
                .maximumRedeliveries(3)
                .redeliveryDelay(TimeUnit.SECONDS.toMillis(10)));
        from("amqp:queue" + deadLetterQueue)
                .routeId("EmailRoute")
                .log("Received message is ${body}")
                .to("velocity:templates/notification.vm?connectCache=true")
                .log("${body}")
                .setHeader("subject", constant("OLAM CTRM App Email Alerts."))
                .setHeader("from", constant("victor_munna@hotmail.com"))
                .setHeader("to", constant("victor.munna@hotmail.com"))
                .setHeader(Exchange.CONTENT_TYPE, constant("text/html"))
                .log("Send Email Now.")
                .to("smtp://localhost:25?debugMode=true");
    }
}
