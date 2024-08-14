package com.techlabs.service;

import com.techlabs.dto.CustomerDTO;
import com.techlabs.dto.LoginDTO;
import com.techlabs.dto.LoginResponseDTO;
import com.techlabs.entity.Registered;
import com.techlabs.repository.RegisteredRepository;
import com.techlabs.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class AuthenticationServiceImp implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailService emailService;
    private final RegisteredRepository registeredRepository;


    public AuthenticationServiceImp(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                                    EmailService emailService, RegisteredRepository registeredRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.emailService = emailService;
        this.registeredRepository = registeredRepository;
    }

    @Override
    public LoginResponseDTO loginUser(LoginDTO loginDto) {
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(), loginDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateToken(authentication);
            return new LoginResponseDTO(Integer.parseInt(loginDto.getUsername()),token);
        }
        catch(Exception e){
            throw new BadCredentialsException("wrong username or password");
        }
    }

    @Override
    public int registerUser(CustomerDTO customerDTO) throws IOException {
        Registered registeredUser = new Registered(customerDTO.getFirstName(),customerDTO.getLastName(),
                customerDTO.getNomineeName(),customerDTO.getAddress(),customerDTO.getEmail(),
                customerDTO.getUniqueIdentificationNumber());
         registeredUser=registeredRepository.save(registeredUser);
        emailService.sendEmailToAdmins(customerDTO);
        return registeredUser.getId();
    }

    @Override
    public void uploadandStorefile(int registeredId, MultipartFile file) throws IOException {
        String path="C:/Users/yadav/Pictures/Camera Roll/customerdocs/"+file.getOriginalFilename();
        Files.copy(file.getInputStream(), Path.of(path));
    }
}
