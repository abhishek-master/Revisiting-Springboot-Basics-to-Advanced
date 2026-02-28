package com.abhishek.AOP.code.services.impl;

import com.abhishek.AOP.code.CustomAnnotation;
import com.abhishek.AOP.code.services.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CartServiceImpl implements CartService {

    @Override
    @CustomAnnotation
    public String modifyCart(Long id) {
        log.info("Inside the Cart Service !!");
        return "service executed successfully !!!!!" ;
    }
}
