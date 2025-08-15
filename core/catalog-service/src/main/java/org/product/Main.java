package org.product;

import org.product.entity.categories.model.Categories;
import org.product.entity.chapter.model.Chapter;
import org.product.entity.product.model.Product;
import org.product.infrastructure.categories.gateway.CategoriesService;
import org.product.infrastructure.chapter.gateway.ChapterService;
import org.product.infrastructure.product.model.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    //@Bean
    CommandLineRunner cmd(CategoriesService categoriesService, ChapterService chapterService,ProductService productService) {
        return args -> {
            categoriesService.save(new Categories("Smartphones and gadgets", "https://example.com/smartphones.jpg")).subscribe();
            categoriesService.save(new Categories("PC, Laptops, Periphery", "https://example.com/laptops.jpg")).subscribe();

            chapterService.save(new Chapter("Smartphones", categoriesService.findByName("Smartphones and gadgets").block().getId())).subscribe();
            chapterService.save(new Chapter("Laptops", categoriesService.findByName("PC, Laptops, Periphery").block().getId())).subscribe();
            chapterService.save(new Chapter("PC", categoriesService.findByName("PC, Laptops, Periphery").block().getId())).subscribe();

            Product p = new Product();
            p.setName("Iphone 14");
            p.setDescription("Latest Apple iPhone with advanced features");
            p.setChapterId(chapterService.findByName("Smartphones").block().getId());
            p.setPrice(BigDecimal.valueOf(150_000));
            
            productService.save(p).subscribe();
        };
    }
}
