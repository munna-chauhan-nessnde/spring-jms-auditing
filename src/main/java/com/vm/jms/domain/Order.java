/**
 * The persistent class for the order database table.
 */
package com.vm.jms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Victor Munna
 */
@Entity
@Table(name = "ORDER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = -6669777807167682166L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private int orderId;

    @Column(name = "ORDER_STATUS")
    private int orderStatus;

    @Temporal(TemporalType.DATE)
    @Column(name = "SALE_DATE")
    private Date saleDate;

/*    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;*/

    @ManyToOne
    @JsonIgnore
   // @JsonManagedReference
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "personId")
    private Person customer;

    @ManyToOne
   // @JsonManagedReference
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "personId")
    private Person employee;

}