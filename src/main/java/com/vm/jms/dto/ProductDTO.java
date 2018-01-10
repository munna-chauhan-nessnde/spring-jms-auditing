package com.vm.jms.dto;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

/**
 * @author Victor Munna
 */
@Data
public class ProductDTO {

    @JMap
    private int id;
    @JMap
    private String name;
    @JMap
    private String description;
    @JMap
    private int quantityPerUnit;
    @JMap
    private double unitPrice;

}
