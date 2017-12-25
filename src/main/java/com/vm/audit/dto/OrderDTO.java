/**
 * DTO to mapping of Order attributes
 */
package com.vm.audit.dto;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

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
    private String saleDate;
    @JMap
    private int customer;
    @JMap
    private int employee;

}
