package com.vm.jms.domain.audit;

import com.vm.jms.util.audit.EventListenerForAction;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class BaseAuditEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUDIT_ID")
    private Integer auditId;

    @Column(name = "AUDIT_ACTION")
    private String auditAction;

    @PrePersist
    private void setBeforeInsert(){
        EventListenerForAction eventListenerForAction = new EventListenerForAction();
        this.auditAction = eventListenerForAction.getAuditAction();
    }
}
