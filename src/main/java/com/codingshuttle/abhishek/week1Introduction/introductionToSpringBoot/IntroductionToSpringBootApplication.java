package com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot;

import com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.basics.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IntroductionToSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntroductionToSpringBootApplication.class, args);

	}
}
