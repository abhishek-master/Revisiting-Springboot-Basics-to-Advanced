package com.abhishek.AOP.code.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AnnotationAopTest {

    @Transactional
    public void testAnnotationAop(){
        log.info("Testing AOP calls based on Annotations !!!");
    }
}

/*
* While running the test case involving this methods excution two logs were logged :
*
* c.a.AOP.code.aspect.LoggingAspect        : I was called before all methods annotated by transactional !!!
* c.a.AOP.code.aspect.LoggingAspect        : I WAS CALLED ONLY IN LISTENER PACKAGE !
* c.a.A.code.listeners.AnnotationAopTest   : Testing AOP calls based on Annotations !!!
*
* And it makes complete sense why !!
* */