package com.abhishek.AOP.code.services;

public interface ShipmentService {
    String orderPackage(Long orderId);
    String trackPackage(Long orderId);
}
