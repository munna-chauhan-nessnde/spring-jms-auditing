/**
 * Methods for accessing the database
 */
package com.vm.audit.repository;

import com.vm.audit.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Victor Munna
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    /**
     * @param orderId
     * @param customer
     * @param employee
     * @param date
     * @param orderStatus
     * @return
     */
    List<Order> findByOrderIdOrCustomerIdOrEmployeeIdOrSaleDateOrOrderStatus(int orderId,
                                                                             int customer,
                                                                             int employee,
                                                                             Date date,
                                                                             int orderStatus);

    /**
     * @param orderId
     * @param customerName
     * @param customerLastName
     * @param employee
     * @param employeeLastName
     * @param date
     * @param orderStatus
     * @return
     */
    List<Order> findByOrderIdOrCustomerFirstNameOrCustomerLastNameOrEmployeeFirstNameOrEmployeeLastNameOrSaleDateOrOrderStatus(
            int orderId,
            String customerName,
            String customerLastName,
            String employee,
            String employeeLastName,
            Date date,
            int orderStatus);

}
