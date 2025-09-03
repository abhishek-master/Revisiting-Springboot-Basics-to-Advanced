package com.revisiting.jpa.hibernate.spring_data_jpa.repositories;

import com.revisiting.jpa.hibernate.spring_data_jpa.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByTitle(String title);
    List<ProductEntity> findByCreatedAtAfter(LocalDateTime after);

    List<ProductEntity> findByQuantityAndPrice(Integer quantity, BigDecimal price);
    List<ProductEntity> findByQuantityGreaterThanAndPriceLessThan (Integer quantity, BigDecimal price);
    List<ProductEntity> findByTitleLike (String s);
    List<ProductEntity> findByTitleContainingIgnoreCase(String s) ;

    /*
    * Below query is written in JPQL, and we need to make sure we use Entity in the query, JPQL understands Java not SQL so even if
    * the table name is "product" we need to write "ProductEntity" -which is the class name- also we need to write column names
    * as property of entity class as we are dealing with JAVA here no SQL is involved. Internally by Hibernate or any JPA
    * provider this query will be converted.
    * */
    @Query("select e from ProductEntity e where e.title ilike :title and e.quantity > :quantity")
   List<ProductEntity> findAllByTitleAndQuantity(String title, int quantity);


    //See how I changed the type for the List to string as title is of the type string
    @Query("select e.title from ProductEntity e where e.title ilike :title and e.quantity > :quantity")
    List<String> findAllTitleByTitleAndQuantity(String title, int quantity);
}
