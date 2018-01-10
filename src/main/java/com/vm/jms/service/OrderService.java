/**
 * List of methods signature
 */
package com.vm.jms.service;

import com.vm.jms.domain.Order;
import com.vm.jms.dto.OrderDTO;

import java.util.List;

/**
 * @author Victor Munna
 */
public interface OrderService {

    /**
     * Retrieves all orders from database
     *
     * @return
     */
    List<Order> findAll();

    /**
     * Finds the order by id or customer, employee, date or orderStatus
     *
     * @param id
     * @param customer
     * @param employee
     * @param date
     * @param orderStatus
     * @return
     */
    List<Order> findByText(String id, String customer, String employee, String date, String orderStatus);

    /**
     * Adds a new order to the database
     *
     * @param orderDTO
     * @return
     */
    String save(OrderDTO orderDTO);

    /**
     * Updates a order to the database
     *
     * @param orderDTO
     * @return
     */
    String update(OrderDTO orderDTO);

    /**
     * Deletes a order by Id from database
     *
     * @param id
     * @return
     */
    String deleteById(int id);

}
