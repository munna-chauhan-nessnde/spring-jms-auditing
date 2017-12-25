/**
 * The persistent class for the products database table.
 */
package com.vm.audit.domain;

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
@Table(name = "PRODUCTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 7309958533611524176L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private int productId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "NAME")
    private String name;

    @Column(name = "QUANTITY_PER_UNIT")
    private int quantityPerUnit;

    @Column(name = "UNIT_PRICE")
    private double unitPrice;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

}