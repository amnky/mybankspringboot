package com.techlabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String rawPassword = "101"; // Replace with your actual password
//		String encodedPassword = passwordEncoder.encode(rawPassword);
//		System.out.println("hasedpassword is:"+encodedPassword);
//		$2a$10$/aXxcBOP.5/eVCVMvGHCye9zOzmbM6aIVEJi21fFAMsfk.FYsQQ86
//		$2a$10$rLuufvDdU9CWmCkaUEMWROWPTEeX8q0B9TyqEvT5CvDpEnKAWoaO.
		SpringApplication.run(Application.class, args);
	}

}
