package com.vm.jms.config;

import lombok.extern.apachecommons.CommonsLog;
import org.apache.camel.component.amqp.AMQPComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.stereotype.Component;

@Component
@CommonsLog
public class JMSConnectionConfig {

    @Value("${service_bus.consumerQueue}")
    private String consumerQueue;

    @Value("${service_bus.deadLetterQueue}")
    private String deadLetterQueue;

    @Value("${jms.username}")
    private String userName;

    @Value("${jms.password}")
    private String password;

    @Value("${jms.remoteUri}")
    private String remoteUri;

    @Bean
    public JmsConnectionFactory jmsConnectionFactory(){
        JmsConnectionFactory jmsConnectionFactory = new JmsConnectionFactory(userName, password, remoteUri);
        jmsConnectionFactory.setReceiveLocalOnly(true);
        return jmsConnectionFactory;
    }
    @Bean
    @Primary
    public CachingConnectionFactory cachingConnectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setTargetConnectionFactory(jmsConnectionFactory());
        return cachingConnectionFactory;
    }
    @Bean
    public JmsConfiguration jmsConfiguration(){
        JmsConfiguration jmsConfig= new JmsConfiguration();
        jmsConfig.setConnectionFactory(cachingConnectionFactory());
        jmsConfig.setCacheLevelName("CACHE_AUTO");
        return jmsConfig;
    }
    @Bean
    public AMQPComponent amqpComponent(){
        AMQPComponent amqp = new AMQPComponent();
        amqp.setConfiguration(jmsConfiguration());
        return amqp;
    }
}
