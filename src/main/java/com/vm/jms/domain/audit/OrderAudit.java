/**
 * The persistent class for the order database table.
 */
package com.vm.jms.domain.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Victor Munna
 */
@Entity
@Table(name = "ORDER_AUDIT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderAudit extends BaseAuditEntity  {

    private static final long serialVersionUID = -6669777807167682166L;

    @Column(name = "ORDER_ID")
    private int orderId;

    @Column(name = "ORDER_STATUS")
    private int orderStatus;

    @Temporal(TemporalType.DATE)
    @Column(name = "SALE_DATE")
    private Date saleDate;

    @OneToMany(mappedBy = "orderId")
    private List<OrderDetailAudit> orderDetails;

    @ManyToOne
    @JsonIgnore
    //@JsonManagedReference
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "personId")
    private PersonAudit customer;

    @ManyToOne
   //@JsonManagedReference
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "personId")
    private PersonAudit employee;

}