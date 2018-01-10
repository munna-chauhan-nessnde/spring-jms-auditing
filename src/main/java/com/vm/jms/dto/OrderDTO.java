/**
 * DTO to mapping of OrderAudit attributes
 */
package com.vm.jms.dto;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Victor Munna
 */
@Data
public class OrderDTO {

    @JMap
    private int id;
    @JMap
    private int orderStatus;
    @JMap
    @Temporal(TemporalType.DATE)
    private String saleDate;
    @JMap
    private int customer;
    @JMap
    private int employee;

}
