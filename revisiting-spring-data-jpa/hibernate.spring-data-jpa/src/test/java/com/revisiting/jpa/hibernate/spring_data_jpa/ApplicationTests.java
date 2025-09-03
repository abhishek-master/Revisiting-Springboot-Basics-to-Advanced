package com.revisiting.jpa.hibernate.spring_data_jpa;

import com.revisiting.jpa.hibernate.spring_data_jpa.entities.ProductEntity;
import com.revisiting.jpa.hibernate.spring_data_jpa.repositories.ProductRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private ProductRepository productRepository;

    @Test
	void contextLoads() {
	}

    @Test
    void testRepository () {
        ProductEntity productX = ProductEntity.builder()
                .title("Kurkure Chataka Masala")
                .sku("ChatakaKurkure")
                .quantity(333)
                .price(BigDecimal.valueOf(19.50))
                .build();
        productRepository.save(productX);
    }

    @Test
    void getRepository () {
        // List<ProductEntity> listOfProducts = productRepository.findByCreatedAtAfter(LocalDateTime.of(2025, 9, 3, 0, 0 ,0));
        // List<ProductEntity> listOfProducts = productRepository.findByQuantityAndPrice(4, BigDecimal.valueOf(42.5));
        // List<ProductEntity> listOfProducts = productRepository.findByQuantityGreaterThanAndPriceLessThan(1000, BigDecimal.valueOf(42.5));
        // List<ProductEntity> listOfProducts =productRepository.findByTitleLike("%kure%");
        //List<ProductEntity> listOfProducts =productRepository.findByTitleContaining('kure');
        //List<ProductEntity> listOfProducts =productRepository.findByTitleContainingIgnoreCase("KURE");
        List<String> listOfProducts = productRepository.findAllTitleByTitleAndQuantity("%Chataka%", 1);
        System.out.println("LIST RESULT AFTER 2st Sept :::" + listOfProducts.toString());
    }
}
