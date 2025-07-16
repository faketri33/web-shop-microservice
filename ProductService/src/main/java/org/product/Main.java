package org.product;

import org.product.entity.product.model.Product;
import org.product.infastructure.product.model.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    //@Bean
    CommandLineRunner cmd(ProductService productService){
        return args -> {

            Product p = new Product();
            p.setName("Iphone 14");
            p.setPrice(BigDecimal.valueOf(150_000));

            productService.save(p).subscribe();
        };
    }
}
