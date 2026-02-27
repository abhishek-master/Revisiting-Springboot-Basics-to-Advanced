package com.abhishek.AOP.code.services.impl;

import com.abhishek.AOP.code.services.ShipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShipmentServiceImpl implements ShipmentService {

    public String orderPackage(Long orderId){
        try{
            log.info("Processing the order ");
            Thread.sleep(1000);

        }catch(Exception e){
            log.error("Error occurred while processing the order");
        }
        return "Order has been processed successfully for the order : " + orderId ;
    }

    @Override
    public String trackPackage(Long orderId) {
        try{
            log.info("Tracking the order ...");
            Thread.sleep(2000);
            throw new RuntimeException("Exception Occurred during track package");

        }catch(Exception e ){
            throw new RuntimeException(e);
        }
    }


}
