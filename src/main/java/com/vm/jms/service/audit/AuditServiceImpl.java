package com.vm.jms.service.audit;

import com.vm.jms.util.audit.EventListenerForAction;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.camel.Body;
import org.apache.camel.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@CommonsLog
@Repository
@Transactional
public class AuditServiceImpl implements AuditService {

    @Autowired
    private EventListenerForAction eventListenerForAction;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(@Body Object object, @Header("actionType") String actionType) {

        log.info("@Header actionType: "+actionType+"@Body entity: "+object.toString());
        eventListenerForAction.setAuditAction(actionType);
        log.info("After setAuditAction @Body entity: "+object.toString());
        entityManager.persist(object);
        return;


    }
}
