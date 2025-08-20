package org.catalog;

import org.catalog.infrastructure.categories.gateway.CategoriesService;
import org.catalog.infrastructure.chapter.gateway.ChapterService;
import org.catalog.infrastructure.product.model.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.catalog", "org.shared"})
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    CommandLineRunner cmd(CategoriesService categoriesService, ChapterService chapterService,ProductService productService) {
        return args -> {
            /*categoriesService.save(new Categories("Smartphones and gadgets", "https://example.com/smartphones.jpg"))
                    .flatMap(savedCategory ->
                            chapterService.save(new Chapter("Smartphones", savedCategory.getId()))
                                    .flatMap(savedChapter -> {
                                        Product p = new Product();
                                        p.setName("Iphone 14");
                                        p.setDescription("Latest Apple iPhone with advanced features");
                                        p.setChapterId(savedChapter.getId());
                                        p.setPrice(BigDecimal.valueOf(150_000));

                                        Product p1 = new Product();
                                        p1.setName("Samsung 25");
                                        p1.setDescription("Latest Samsung with advanced features");
                                        p1.setChapterId(savedChapter.getId());
                                        p1.setPrice(BigDecimal.valueOf(125_000));

                                        return productService.save(p)
                                                .and(productService.save(p1))
                                                .thenReturn(savedChapter);
                                    })
                    )
                    .subscribe();


            categoriesService.save(new Categories("PC, Laptops, Periphery", "https://example.com/laptops.jpg"))
                    .flatMap(savedCategory ->
                            Mono.when(
                                    chapterService.save(new Chapter("Laptops", savedCategory.getId()))
                                            .flatMap(savedChapter -> {
                                                Product p2 = new Product();
                                                p2.setName("Samsung book 3");
                                                p2.setDescription("Latest Samsung with advanced features");
                                                p2.setChapterId(savedChapter.getId());
                                                p2.setPrice(BigDecimal.valueOf(225_000));

                                                return productService.save(p2, Mono.just()).thenReturn(savedChapter);
                                            }),
                                    chapterService.save(new Chapter("PC", savedCategory.getId()))
                            )
                    )
                    .subscribe();*/
        };
    }
}
