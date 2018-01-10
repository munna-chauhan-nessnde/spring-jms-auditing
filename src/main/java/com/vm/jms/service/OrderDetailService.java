package com.vm.jms.service;

import com.vm.jms.domain.OrderDetail;
import com.vm.jms.domain.OrderDetailPK;
import com.vm.jms.dto.OrderDetailDTO;

import java.util.List;

/**
 * @author Victor Munna
 */
public interface OrderDetailService {

    /**
     * Retrieves all order details from database
     *
     * @return
     */
    List<OrderDetail> findAll();

    /**
     * Finds the order detail by id or customer, employee, date or orderStatus
     *
     * @param productId
     * @param orderId
     * @return
     */
    OrderDetail findByDetailPk(String productId, String orderId);

    /**
     * Adds a new order detail to the database
     *
     * @param orderDetailDTO
     * @return
     */
    String save(OrderDetailDTO orderDetailDTO);

    /**
     * Updates a order detail to the database
     *
     * @param orderDetailDTO
     * @return
     */
    String update(OrderDetailDTO orderDetailDTO);

    /**
     * Deletes a order detail by orderDetailPK from database
     *
     * @param orderDetailPK
     * @return
     */
    String deleteById(OrderDetailPK orderDetailPK);

}
