package com.revisiting.jpa.hibernate.spring_data_jpa.service;
import com.revisiting.jpa.hibernate.spring_data_jpa.entities.ProductEntity;
import com.revisiting.jpa.hibernate.spring_data_jpa.repositories.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final int PAGE_SIZE = 5 ;
    private final ProductRepository productRepository ;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<ProductEntity> getResults(String title) {
        String queryString = "%" + title + "%" ;
        List<ProductEntity> list = productRepository.findByTitleOrderByTitleAsc(queryString);
        return list ;
    }

    public List<ProductEntity> getSortedList(String sortBy) {
        Sort sort = Sort.by(Sort.Order.desc(sortBy));
        List<ProductEntity> sortedList = productRepository.findAll(Sort.by(Sort.Order.desc(sortBy)));
        return sortedList;
    }

    public List<ProductEntity> getSortedListBySku() {
        Sort sortByChained = Sort.by(Sort.Order.desc("sku"), Sort.Order.asc("id"));
        Sort sortBy = Sort.by(Sort.Direction.DESC, "sku");
        return productRepository.findAll(sortBy);
    }
    public List<ProductEntity> getSortedByName () {
        return productRepository.findByTitle("Coke", Sort.by(Sort.Order.desc("id")));
    }

    public List<ProductEntity> getPaginatedAndSortedList(String s, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(Sort.Order.asc("title")));
        return productRepository.findByTitleContaining(s, page);
    }
}
