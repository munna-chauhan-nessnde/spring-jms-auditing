package com.vm.jms.service;

import com.googlecode.jmapper.JMapper;
import com.vm.jms.domain.OrderDetail;
import com.vm.jms.domain.OrderDetailPK;
import com.vm.jms.dto.OrderDetailDTO;
import com.vm.jms.repository.OrderDetailRepository;
import com.vm.jms.util.Utilities;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Victor Munna
 */
@Service
@Transactional
@CommonsLog
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    private String response;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetail findByDetailPk(String productId, String orderId) {
        Integer prodId = Utilities.isInteger(productId);
        Integer ordId = Utilities.isInteger(orderId);
        return orderDetailRepository.findById(new OrderDetailPK(prodId, ordId));
    }

    @Override
    public String save(OrderDetailDTO orderDetailDTO) {
        response = "OrderAudit Detail saved!";
        JMapper<OrderDetail, OrderDetailDTO> mapper = new JMapper<>(OrderDetail.class, OrderDetailDTO.class);
        OrderDetail orderDetail = mapper.getDestination(orderDetailDTO);
        log.info("JMapper: " + orderDetail.toString());
        orderDetailRepository.save(orderDetail);
        log.info(response);
        return response;
    }

    @Override
    public String update(OrderDetailDTO orderDetailDTO) {
        response = "OrderAudit Detail updated!";
        OrderDetail orderDetail = orderDetailRepository.findOne(orderDetailDTO.getId());
        orderDetail = this.updateOrderDetail(orderDetail, orderDetailDTO);
        orderDetailRepository.save(orderDetail);
        log.info(response);
        return response;
    }

    @Override
    public String deleteById(OrderDetailPK orderDetailPK) {
        response = "OrderAudit Detail deleted!";
        orderDetailRepository.delete(orderDetailPK);
        return response;
    }

    private OrderDetail updateOrderDetail(OrderDetail orderDetail,
                                          OrderDetailDTO orderDetailDTO) {
        orderDetail.setUnitPrice(orderDetailDTO.getUnitPrice());
        orderDetail.setDiscount(orderDetailDTO.getDiscount());
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        return orderDetail;
    }

}
