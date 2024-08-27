package com.techlabs.service;

import com.techlabs.dto.CustomerDTO;
import com.techlabs.dto.LoginDTO;
import com.techlabs.dto.LoginResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AuthenticationService {
    LoginResponseDTO loginUser(LoginDTO loginDTO);

    int registerUser(CustomerDTO customerDTO) throws IOException;

    void uploadandStorefile(int registeredId, int uniqueId, MultipartFile file) throws IOException;
}
