package com.vm.audit.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Victor Munna
 */

@Entity
@Table(name = "PERSON")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    private static final long serialVersionUID = -8284413697324924501L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @JsonBackReference
    @OneToMany(mappedBy = "customer")
    private List<Order> customers;

    @JsonBackReference
    @OneToMany(mappedBy = "employee")
    private List<Order> employees;
}
