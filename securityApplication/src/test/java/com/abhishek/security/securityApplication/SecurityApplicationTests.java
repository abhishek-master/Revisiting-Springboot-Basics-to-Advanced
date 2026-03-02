package com.abhishek.security.securityApplication;

import com.abhishek.security.securityApplication.entities.User;
import com.abhishek.security.securityApplication.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityApplicationTests {

    @Autowired
    private JwtService jwtService ;

	@Test
	void contextLoads() {
//        User user = new User(1L, "sinha.abhishek18nov@gmail.com", "pass@123");
//
//        String token = jwtService.generateToken(user);
//
//        System.out.println(token);
//
//        Long id = jwtService.getUserIdFromToken(token);
//
//        System.out.println("ID :::: " + id);
	}

}
