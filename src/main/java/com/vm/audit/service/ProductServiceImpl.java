package com.vm.audit.service;

import com.googlecode.jmapper.JMapper;
import com.vm.audit.domain.Product;
import com.vm.audit.dto.ProductDTO;
import com.vm.audit.repository.ProductRepository;
import com.vm.audit.util.Utilities;
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
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private String response;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findByText(String id, String name) {
        Integer productId = Utilities.isInteger(id);
        return productRepository.findByProductIdOrName(productId, name);
    }

    @Override
    public String save(ProductDTO productDTO) {
        response = "Product saved!";
        JMapper<Product, ProductDTO> mapper = new JMapper<>(Product.class, ProductDTO.class);
        Product product = mapper.getDestination(productDTO);
        log.info("JMapper: " + product.toString());
        productRepository.save(product);
        log.info(response);
        return response;
    }

    @Override
    public String update(ProductDTO productDTO) {
        response = "Product updated!";
        Product product = productRepository.findOne(productDTO.getId());
        product = this.updateProduct(product, productDTO);
        productRepository.save(product);
        log.info(response);
        return response;
    }

    @Override
    public String deleteById(int id) {
        response = "Product deleted!";
        productRepository.delete(id);
        log.info(response);
        return response;
    }

    private Product updateProduct(Product product, ProductDTO productDTO) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setQuantityPerUnit(productDTO.getQuantityPerUnit());
        product.setUnitPrice(productDTO.getUnitPrice());
        return product;
    }

}
