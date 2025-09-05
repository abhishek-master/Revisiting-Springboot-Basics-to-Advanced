package com.revisiting.jpa.hibernate.spring_data_jpa.repositories;

import com.revisiting.jpa.hibernate.spring_data_jpa.entities.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    //Note that in all the below mehtods I can pass "Sort" OR "Pageable" as a parameters
    //SpringBoot provides us excellent way to use Sorting and Pagination in the application extremely fast.
    List<ProductEntity> findByTitle(String title);
    List<ProductEntity> findByTitle(String title, Sort sort);
    List<ProductEntity> findByTitle(String title, Pageable page);
    List<ProductEntity> findByCreatedAtAfter(LocalDateTime after);
    List<ProductEntity> findByQuantityAndPrice(Integer quantity, BigDecimal price);
    List<ProductEntity> findByQuantityGreaterThanAndPriceLessThan (Integer quantity, BigDecimal price);
    List<ProductEntity> findByTitleLike (String s);
    List<ProductEntity> findByTitleContainingIgnoreCase(String s) ;

    /*
     **************************************************************************************
    * JPQL and dynamic method naming
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

    List<ProductEntity> findByTitleOrderByPrice(String title);
    List<ProductEntity> findAllContainingTitleOrderByTitle(String title);
    List<ProductEntity> findAllByTitleLike(String title);
    List<ProductEntity> findByTitleOrderByTitleAsc(String queryString);

    /*
    * How to use "Sort" in the JPA method's parameter:
    * 2 ways :
    * Sort sort = Sort.by(Sort.Direction.ASC, sortField);
    * Say some entities have same comparing value, like I am comparing by title and more than one items has same name
    * In that case we can chain with parameters, like if they are smae then which parameter can be used to compare
    *
    * */
    Sort sort = Sort.by(Sort.Direction.DESC, "sortField");
    Sort sortChaining = Sort.by(Sort.Order.asc("field_1"), Sort.Order.desc("field2"));
    //And use it like below;
    /*
    * List<ProductEntity> getSortedList(String str){
    * return productRepository.findAll(Sort.Order.desc("fieldName"))
    * }
    * */
    /*
**************************************************************************************
                                    #PAGINATION#
    * Using Pageable for Pagination
    * Page<ProductEntity> findAll(Pageable page);
    * Page<ProductEntity> findByTitle(Pageable page);
**************************************************************************************
    * CREATING A PAGEABLE INSTANCE
    * Pageable page =  PageRequest.of(pageNumber, size, Sort.by("lastName").ascending()); // SortedPaginated Result
    * Pageable page =  PageRequest.of(pageNumber, size); //Normal Paginated result
    * */

    //Note that in all the below mehtods I can pass "Sort" OR "Pageable" as a parameters, NOT BOTH
    //List<ProductEntity> findByTitleContaining(String s, Pageable page, Sort sortByTitle); //Incorrect as we are passing both page and SORT, we can
    //sort the Page itself then why to redo it ?
    List<ProductEntity> findByTitleContaining(String s, Pageable page);
}



