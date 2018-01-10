package com.vm.jms.util.audit;

import lombok.extern.apachecommons.CommonsLog;
import org.apache.camel.Header;
import org.springframework.stereotype.Component;

@Component
@CommonsLog
public class EventListenerForAction {

    private String auditAction;

    public String getAuditAction() {
        log.info("Get the Audit Action: "+auditAction);
        return auditAction;
    }

    public void setAuditAction(@Header("actionType") String auditAction) {
        log.info("Set the Audit Action: "+auditAction);
        this.auditAction = auditAction;
    }
}
