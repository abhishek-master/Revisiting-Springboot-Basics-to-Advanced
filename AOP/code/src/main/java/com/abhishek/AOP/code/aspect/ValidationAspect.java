package com.abhishek.AOP.code.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect
public class ValidationAspect {

    @Pointcut("execution(* com.abhishek.AOP.code.services.impl.*.*modifyCart(..))")
    public void allServiceMethodPointCut(){

    }

    @Around("allServiceMethodPointCut()")
    public Object validateOrderId(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Object args[] = proceedingJoinPoint.getArgs();
        Long orderId = (Long)args[0];
        if(orderId > 0){
            System.out.println("INSIDE THE CUSTOM Valid ---> @Around ");
                return proceedingJoinPoint.proceed();
        }
        return "Cannot call with negative order id !!";
    }

    //Here using proceedingJoinPoint as it is more helpful to catch and halt or resume
    // the execution of the method.
    /*
    In the above method we are adding a validation check where, we are verifying if the
    parameter value is greater than 0 or not. A typical custom implementation of
    @Valid annotation
    A very common case of Around is we can log execution time of any method
    */
}
