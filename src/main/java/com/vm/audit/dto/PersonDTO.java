/**
 * DTO to mapping of Person attributes
 */
package com.vm.audit.dto;

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
