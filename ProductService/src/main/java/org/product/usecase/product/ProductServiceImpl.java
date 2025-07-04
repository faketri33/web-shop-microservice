package org.product.usecase.product;


import org.product.entity.product.gateway.ProductRepository;
import org.product.entity.product.model.Product;
import org.product.infastructure.product.model.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = Logger.getLogger(ProductServiceImpl.class.getName());

    @Autowired
    private ProductRepository productService;

    public Flux<Product> findAll(){
        LOGGER.info("Find all product");
        return productService.findAll();
    }

    public Mono<Product> save(Product p){
        LOGGER.info("Save product - " + p.getName());
        return productService.save(p);
    }
}
