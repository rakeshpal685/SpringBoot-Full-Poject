package com.java.fullProject.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

/*    @Test
    public void saveProduct() {
        Product product = Product.builder()
                .sku("1000")
                .name("mobile")
                .description("This is a mobile")
                .price(BigDecimal.valueOf(1245.98))
                .active(true)
                .imageUrl("http://abc.com")
                .build();
        productRepository.save(product);
    }*/

}