/**
 * The primary key class for the order_details database table.
 */
package com.vm.audit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Victor Munna
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailPK implements Serializable {

    private static final long serialVersionUID = 1125212530915683308L;

    @Column(name = "PRODUCT_ID", insertable = false, updatable = false)
    private int productId;

    @Column(name = "ORDER_ID", insertable = false, updatable = false)
    private int orderId;

}