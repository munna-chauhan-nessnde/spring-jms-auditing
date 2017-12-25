package com.vm.audit.dto;

import com.vm.audit.domain.OrderDetailPK;
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
