package com.vm.jms.repository;

import com.vm.jms.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Victor Munna
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     * Finds the product by id or name
     *
     * @param id
     * @param name
     * @return
     */
    Product findByProductIdOrName(int id, String name);

}
