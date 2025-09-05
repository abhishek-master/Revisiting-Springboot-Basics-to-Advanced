package com.revisiting.jpa.hibernate.spring_data_jpa.controllers;

import com.revisiting.jpa.hibernate.spring_data_jpa.entities.ProductEntity;
import com.revisiting.jpa.hibernate.spring_data_jpa.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Parameter;
import java.util.List;

@RestController
@RequestMapping(path="/product")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{t}")
    public List<ProductEntity> getByTitle(@PathVariable String t){
        List<ProductEntity> productList = productService.getResults(t);
        return productList ;
    }

    @GetMapping("/")
    public List<ProductEntity> getAllItemsSortedList(@RequestParam(defaultValue = "id") String sortBy){
        return productService.getSortedList(sortBy);
    }

    @GetMapping("/paginatedAndSorted")
    public List<ProductEntity> getPaginatedAndSortedList (@RequestParam(defaultValue="kure") String s, @RequestParam(defaultValue="0") Integer pageNumber){
        return productService.getPaginatedAndSortedList(s, pageNumber);
    }


}
