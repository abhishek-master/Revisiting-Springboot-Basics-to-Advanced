package com.abhishek.AOP.code.listeners;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AopWithinTest {


    public void testWithinJoinPoint(){
        log.info("Tested \"within\" point cut !!" );
    }
}
