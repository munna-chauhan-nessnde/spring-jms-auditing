package com.vm.audit.repository;

import com.vm.audit.domain.OrderDetail;
import com.vm.audit.domain.OrderDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Victor Munna
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {

    /**
     * @param orderDetailPK
     * @return
     */
    OrderDetail findById(OrderDetailPK orderDetailPK);

}
