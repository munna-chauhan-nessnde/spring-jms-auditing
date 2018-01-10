package com.vm.jms.service.audit;

import org.apache.camel.Body;
import org.apache.camel.Header;

/**
 * @author Victor Munna
 */
public interface AuditService {

    /**
     * Adds a new product to the database
     *
     * @param object
     * @return
     */
    void save(@Body Object object, @Header("actionType") String actionType);

}
