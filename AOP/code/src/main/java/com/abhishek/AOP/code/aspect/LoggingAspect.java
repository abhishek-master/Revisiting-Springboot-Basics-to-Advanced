package com.abhishek.AOP.code.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Aspect
@Component
@Slf4j
public class LoggingAspect {
    /** Execution kind pointcuts :
     * most widely used pointcut expression. It allows you to target
     * method executions in your classes
     * We can use it in various ways like ---->
     * How we write point cut expressions :
     * '*' ---> return type, followed by
     * 'com.path1.path2.....' followed by
     * 'methodName' followed by
     * '(..) ---> tells method (on which we are applying advice) can take
     * any number of arguments'
    */

//    @Before("execution(* com.abhishek.AOP.code.services.impl.*.*(..))")
//    public void beforeShipmentServiceMethods(){
//        log.info("Before Method @@@@@@@@@@@@@@@@ Call : {}");
//    }

    /**
     * In below I have not defined a path so this order package can
     * be present in any of the class and this Aspect will be injected in all of them
     */

    @Before("execution(* orderPackage(..))")
    public void beforeAllOrderPackageMethods(){
        log.info("Order Package was called :: ");
    }


    /*
    * "within()" when we want to limit the advice to a particular class or package,
    * without focusing on specific methods. For example the below pointcut applies
    * to any pointcut within the "com.abhishek.AOP.code.listeners" package including
    * methods, fields and constructors.
    * */

    @Before("within(com.abhishek.AOP.code.listeners.*)")
    public void withinListenersPackageBeforeAllMethods(){
        log.info("I WAS CALLED ONLY IN LISTENER PACKAGE !");

    }

    /*
    * We use "@annotation" when you want to apply advice to methods annotated
    * with a particular annotation. You can also create your custom annotations for this
    * pointcut.
    * */

    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void logTransactionalMethods(){
        log.info("I was called before all methods annotated by transactional !!!");
    }

    @After("@annotation(com.abhishek.AOP.code.CustomAnnotation)")
    public void logCustomAnnotaton(){
        log.info("I was called after method annotated with CUSTOM ANNOTATION was executed.");
    }






}
