package com.vm.jms.service;

import com.googlecode.jmapper.JMapper;
import com.vm.jms.domain.Order;
import com.vm.jms.domain.Person;
import com.vm.jms.dto.OrderDTO;
import com.vm.jms.repository.OrderRepository;
import com.vm.jms.repository.PersonRepository;
import com.vm.jms.util.Utilities;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Victor Munna
 */
@Service
@Transactional
@CommonsLog
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PersonRepository personRepository;

    private String response;

    public OrderServiceImpl(OrderRepository orderRepository, PersonRepository personRepository) {
        this.orderRepository = orderRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findByText(String id, String customer, String employee, String date, String orderStatus) {
        Integer orderId = Utilities.isInteger(id);
        Integer customerId = Utilities.isInteger(customer);
        Integer employeeId = Utilities.isInteger(employee);
        Date valueDate = Utilities.matchDate(date);

        return (customerId == 0 || employeeId == 0)
                ? orderRepository
                .findByOrderIdOrCustomerFirstNameOrCustomerLastNameOrEmployeeFirstNameOrEmployeeLastNameOrSaleDateOrOrderStatus(
                        orderId, customer, customer, employee, employee, valueDate, orderId)
                : orderRepository
                .findByOrderIdOrCustomerIdOrEmployeeIdOrSaleDateOrOrderStatus(orderId, customerId, employeeId,
                        valueDate, orderId);
    }

    @Override
    public String save(OrderDTO orderDTO) {
        response = "OrderAudit saved!";
        Person customer = personRepository.findOne(orderDTO.getCustomer());
        Person employee = personRepository.findOne(orderDTO.getEmployee());
        JMapper<Order, OrderDTO> mapper = new JMapper<>(Order.class, OrderDTO.class);
        Order order = mapper.getDestination(orderDTO);
        order.setCustomer(customer);
        order.setEmployee(employee);
        log.info("JMapper: " + order.toString());
        orderRepository.save(order);
        log.info(response);
        return response;
    }

    @Override
    public String update(OrderDTO orderDTO) {
        response = "PersonAudit updated!";
        Order order = orderRepository.findOne(orderDTO.getId());
        Person employee = personRepository.findOne(orderDTO.getEmployee());
        Person customer = personRepository.findOne(orderDTO.getCustomer());
        order = this.updateOrder(order, orderDTO, employee, customer);
        orderRepository.save(order);
        log.info(response);
        return response;
    }

    @Override
    public String deleteById(int id) {
        response = "OrderAudit deleted!";
        orderRepository.delete(id);
        log.info(response);
        return response;
    }

    private Order updateOrder(Order order, OrderDTO orderDTO, Person employee, Person customer) {
        order.setCustomer(customer);
        order.setEmployee(employee);
        order.setOrderStatus(orderDTO.getOrderStatus());
        order.setSaleDate(Utilities.matchDate(orderDTO.getSaleDate()));
        return order;
    }

}
