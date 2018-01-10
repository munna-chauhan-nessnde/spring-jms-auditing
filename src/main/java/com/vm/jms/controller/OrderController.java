package com.vm.jms.controller;

import com.vm.jms.domain.Order;
import com.vm.jms.dto.OrderDTO;
import com.vm.jms.service.OrderService;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Victor Munna
 */
@RestController
@RequestMapping(value = "/order")
@CommonsLog
public class OrderController {

    private static final String WELCOME_ORDER_ENTITY = "Welcome to AUDIT example: OrderAudit Entity";

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String init() {
        return WELCOME_ORDER_ENTITY;
    }

    @RequestMapping(value = "/find-all-orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> findAllOrders() {
        log.info("Find all orders");
        return orderService.findAll();
    }

    @RequestMapping(value = "/find-by-order", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> findByTest(@RequestParam(value = "text") String text) {
        log.info(String.format("Finding by: %s", text));
        return orderService.findByText(text, text, text, text, text);
    }

    @RequestMapping(value = "/save-order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveOrder(@RequestBody OrderDTO orderDTO) {
        Validate.notNull(orderDTO, "The order cannot be null");
        log.info(String.format("Saving order: %s", orderDTO.toString()));
        return orderService.save(orderDTO);
    }

    @RequestMapping(value = "/update-order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateOrder(@RequestBody OrderDTO orderDTO) {
        Validate.notNull(orderDTO, "The order cannot be null");
        log.info(String.format("Updating order: %s", orderDTO.toString()));
        return orderService.update(orderDTO);
    }

    @RequestMapping(value = "/delete-order", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteOrder(@RequestParam(value = "id") int id) {
        log.info(String.format("Deleting order: %s", id));
        return orderService.deleteById(id);
    }

}
