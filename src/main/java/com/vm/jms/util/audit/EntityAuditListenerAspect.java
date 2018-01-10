package com.vm.jms.util.audit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Component
@CommonsLog
public class EntityAuditListenerAspect {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Value("${service_bus.consumerQueue}")
    private String consumerQueue;

    private Object id;

    @Before("execution(public * org.springframework.data.repository.Repository+.*(..))")
    public void logAdvice(JoinPoint joinPoint) throws IllegalAccessException {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        if(!Stream.of("saveAndFlush", "save").collect(Collectors.toList()).contains(signature.getName()))
            return;
        Object entity = joinPoint.getArgs()[0];
        if(entity.getClass().getName().contains("Audit"))
            return;
        for(Field field : entity.getClass().getDeclaredFields()){
            if(field.isAnnotationPresent(Id.class)){
                field.setAccessible(true);
                id = field.get(entity);
                break;
            }
        }
    }

    @AfterReturning(value = "execution(public * org.springframework.data.repository.Repository+.*(..))", returning = "result")
    public void setMessageHeader(JoinPoint joinPoint, Object result) throws JsonProcessingException {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        if(!Stream.of("saveAndFlush", "save", "delete").collect(Collectors.toList()).contains(signature.getName()))
            return;
        Object entity = joinPoint.getArgs()[0];
        if(entity.getClass().getName().contains("Audit"))
            return;

        ObjectMapper objectMapper = new ObjectMapper();
        String auditString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
        log.info("Audit Json String: "+auditString);
        Map<String, Object> headers =  new HashMap<>();
        if(id != null){
            if(signature.getName().contains("delete")){
                headers.put("actionType", ActonType.DELETED.toString());
            }else {
                headers.put("actionType", ActonType.UPDATED.toString());
            }
        }else {
            headers.put("actionType", ActonType.INSERTED.toString());
        }
        headers.put(entity.getClass().getSimpleName(), entity.getClass().getSimpleName());
        log.info("Audit Headers: "+headers);

        CompletableFuture.supplyAsync(() -> producerTemplate.sendBodyAndHeaders("amqp:queue"+ consumerQueue, ExchangePattern.InOnly,auditString, headers));
    }
}
