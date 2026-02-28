package com.abhishek.AOP.code.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class LoggingAspectV2 {

    /*
    * We can define the pointcuts so that we do not need to define it again and again
    * services.*.*(..))
    * first '.*' means inside the impl package go one level down (All the classes that impl contains)
    * second '.*' + '(..)' means go to each methods (of those each classes)
    * This below is asn example of named pointcut, in the class LoggingAspect we followed
    * inline pointcut
    * */

    @Pointcut("execution(* com.abhishek.AOP.code.services.impl.*.*(..))")
    public void allServiceMethodsPointcut(){

    }

    @Before("allServiceMethodsPointcut()")
    public void beforeServiceMethodCalls(JoinPoint joinPoint){
        //This joinPoint is very powerful as it gives a lot of data regarding the joinpoint.
        log.info("Before advice method call, {} ", joinPoint.getSignature());
    }

    @AfterReturning(value = "allServiceMethodsPointcut()", returning = "returnedObj")
    public void afterReturningServiceMethodCalls(JoinPoint joinPoint, Object returnedObj){
        log.info("AfterReturning Advice method call, JOINPOINT = {}, RETURNED OBJ = {}", joinPoint.getSignature(), returnedObj);
    }

    @Around("allServiceMethodsPointcut()")
    public Object logTimeExecutionForMethods (ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Long startTime = System.currentTimeMillis() ; //Noted time when execution begin
        Object returnedValue = proceedingJoinPoint.proceed(); // Makes the method execute and return value
        Long endTime = System.currentTimeMillis(); //Noted time when execution ends

        Long diff = endTime - startTime ;
        log.info("Time taken for {} is {}", proceedingJoinPoint.getSignature(), diff);
        return returnedValue ;
    }


}

/**
 * There are a lot of Advice types :
 * Like we have @Before and @After advices below are some more
 * @AfterReturning Advice : It runs only after a normal execution of a method
 * @AfterThrowing Advice : It runs only after an exception is thrown while executing the methods
 *
 * @Around Advice : Most important type of advice.
 * This annotation allows us to take actions either before or after a JoinPoint method is run.
 * We can use it to return a custom value or throw an exception or simply let the method run
 * and return normally.
 * See validation Aspect Class inside aspect package to understand usage
 * */