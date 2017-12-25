/**
 * The persistent class for the order_details database table.
 */
package com.vm.audit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Victor Munna
 */
@Entity
@Table(name = "ORDER_DETAILS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1183975993716362588L;

    @EmbeddedId
    private OrderDetailPK id;

    @Column(name = "DISCOUNT")
    private double discount;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "UNIT_PRICE")
    private double unitPrice;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", insertable = false, updatable = false)
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", insertable = false, updatable = false)
    @JsonIgnore
    private Product product;

}