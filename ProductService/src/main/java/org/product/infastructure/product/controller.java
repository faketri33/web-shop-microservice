package org.product.infastructure.product;

import org.product.entity.product.model.Product;
import org.product.infastructure.product.model.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/product")
public class controller {

    @Autowired
    private ProductService productService;

    @RequestMapping("/list")
    public Flux<Product> findAll(){
        return productService.findAll();
    }
}
