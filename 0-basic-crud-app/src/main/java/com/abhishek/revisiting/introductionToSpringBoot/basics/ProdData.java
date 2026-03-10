package com.abhishek.revisiting.introductionToSpringBoot.basics;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "deploy.env", havingValue = "production")
public class ProdData implements DB {
    String prodData = "PROD DATA !" ;
    public String getData() {
        return prodData;
    }
}
