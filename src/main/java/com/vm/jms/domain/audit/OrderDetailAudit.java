/**
 * The persistent class for the order_details database table.
 */
package com.vm.jms.domain.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Victor Munna
 */
@Entity
@Table(name = "ORDER_DETAILS_AUDIT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailAudit extends BaseAuditEntity  {

    private static final long serialVersionUID = 1183975993716362588L;

    @Column(name = "ORDER_DETAIL_ID")
    private Integer orderDetailId;

    @Column(name = "DISCOUNT")
    private double discount;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "UNIT_PRICE")
    private double unitPrice;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", insertable = false, updatable = false)
    @JsonIgnore
    private OrderAudit order;

    @ManyToOne
    @JoinColumn(name = "FK_ORDER_DETAIL_ID", referencedColumnName = "ORDER_DETAIL_ID")
    //@JsonIgnore
    private OrderDetailAudit fkOrderDetailId;

}