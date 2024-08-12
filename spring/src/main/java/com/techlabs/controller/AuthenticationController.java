package com.techlabs.controller;

import com.techlabs.dto.CustomerDTO;
import com.techlabs.dto.LoginDTO;
import com.techlabs.dto.LoginResponseDTO;
import com.techlabs.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @PostMapping(value = "/register")
    ResponseEntity<Integer> registerUser(@RequestBody CustomerDTO customerDTO) throws IOException {
        int customerId=authenticationService.registerUser(customerDTO);
        return  new ResponseEntity<>(customerId,HttpStatus.OK);
    }
//    @PostMapping(value = "/register/{id}")
//    ResponseEntity<HttpStatus> uploadFile(@PathVariable("id") int registeredId
//            ,@RequestPart("file") MultipartFile file) throws IOException {
//        authenticationService.uploadandStorefile(registeredId,file);
//        return  new ResponseEntity<>(HttpStatus.OK);
//    }

}
