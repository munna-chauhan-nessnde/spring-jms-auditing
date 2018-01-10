package com.vm.jms.dto;

import com.vm.jms.domain.OrderDetailPK;
import lombok.Data;

/**
 * @author Victor Munna
 */
@Data
public class OrderDetailDTO {

    private OrderDetailPK id;
    private double discount;
    private int quantity;
    private double unitPrice;

}
