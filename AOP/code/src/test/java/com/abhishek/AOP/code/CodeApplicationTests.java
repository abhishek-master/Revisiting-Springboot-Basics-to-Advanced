package com.abhishek.AOP.code;

import com.abhishek.AOP.code.listeners.AnnotationAopTest;
import com.abhishek.AOP.code.listeners.AopWithinTest;
import com.abhishek.AOP.code.services.CartService;
import com.abhishek.AOP.code.services.ShipmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodeApplicationTests {

    @Autowired
    private ShipmentService shipmentService ;

    @Autowired
    private CartService cartService ;

    @Autowired
    private AopWithinTest aopWithinTest ;

    @Autowired
    private AnnotationAopTest annotationAopTest ;

    @Test
	void aopTestOrderPackage (){
        shipmentService.orderPackage(1L);
	}

    @Test
    void testAopWithinTest(){
        aopWithinTest.testWithinJoinPoint();
    }


    @Test
    void aopTestTrackPackage (){
        shipmentService.trackPackage(1L);
    }

    @Test
    void aopTestAnnotations () {
        annotationAopTest.testAnnotationAop();
    }

    @Test
    void aopTestCustomAnnotation(){
        String temp = cartService.modifyCart(-8L);
        System.out.println(temp);

    }

}
