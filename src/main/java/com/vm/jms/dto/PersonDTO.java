/**
 * DTO to mapping of PersonAudit attributes
 */
package com.vm.jms.dto;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

/**
 * @author Victor Munna
 */
@Data
public class PersonDTO {

    @JMap
    private int id;
    @JMap
    private String name;
    @JMap
    private String lastName;

}
