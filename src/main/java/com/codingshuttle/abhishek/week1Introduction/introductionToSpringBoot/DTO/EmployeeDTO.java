package com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.DTO;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDTO {

    public EmployeeDTO(Integer id, Integer age, String name, String address, boolean isActive) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.address = address;
        this.isActive = isActive;
    }
    public EmployeeDTO(){}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id ;
    private Integer age;
    private String name;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    private String address;
    private boolean isActive ;

}
