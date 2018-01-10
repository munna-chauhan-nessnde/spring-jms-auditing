package com.vm.jms.domain.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @author Victor Munna
 */

@Entity
@Table(name = "PERSON_AUDIT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonAudit extends BaseAuditEntity {

    private static final long serialVersionUID = -8284413697324924501L;

    @Column(name = "PERSION_ID")
    private int personId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    //@JsonBackReference
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<OrderAudit> customers;

    //@JsonBackReference
    @OneToMany(mappedBy = "employee")
    private List<OrderAudit> employees;
}
