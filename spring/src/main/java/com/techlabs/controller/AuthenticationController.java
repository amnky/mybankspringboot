package com.techlabs.controller;

import com.techlabs.dto.CustomerDTO;
import com.techlabs.dto.LoginDTO;
import com.techlabs.dto.LoginResponseDTO;
import com.techlabs.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginDTO loginDTO){
        LoginResponseDTO loginResponseDTO=authenticationService.loginUser(loginDTO);
        return  new ResponseEntity<>(loginResponseDTO,HttpStatus.OK);
    }

    @PostMapping("/register")
    ResponseEntity<Integer> registerUser(@RequestBody CustomerDTO customerDTO){
        int customerId=authenticationService.registerUser(customerDTO);
        return  new ResponseEntity<>(customerId,HttpStatus.OK);
    }
}
