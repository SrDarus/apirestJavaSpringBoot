package com.darus.apispringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
//public class ApiSpringBootApplication implements CommandLineRunner {
	public class ApiSpringBootApplication {

//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public static void main(String[] args){
		SpringApplication.run(ApiSpringBootApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		String password= "12345";
//		for (int i = 0; i<4; i++) {
//			String passwordBScript = passwordEncoder.encode(password);
//			System.out.println("Password: "+ passwordBScript);
//		}
//	}

}
