package com.techlabs.service;

import com.techlabs.dto.CustomerDTO;
import com.techlabs.dto.LoginDTO;
import com.techlabs.dto.LoginResponseDTO;

public interface AuthenticationService {
    LoginResponseDTO loginUser(LoginDTO loginDTO);

    int registerUser(CustomerDTO customerDTO);
}
