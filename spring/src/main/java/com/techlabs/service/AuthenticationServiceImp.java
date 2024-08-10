package com.techlabs.service;

import com.techlabs.dto.CustomerDTO;
import com.techlabs.dto.CustomerResponseDTO;
import com.techlabs.dto.LoginDTO;
import com.techlabs.dto.LoginResponseDTO;
import com.techlabs.entity.Registered;
import com.techlabs.repository.AuthRepository;
import com.techlabs.repository.RegisteredRepository;
import com.techlabs.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImp implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomerService customerService;

    private final RegisteredRepository registeredRepository;


    public AuthenticationServiceImp(AuthenticationManager authenticationManager,
                                    AuthRepository authRepository,
                                    PasswordEncoder passwordEncoder,
                                    JwtTokenProvider jwtTokenProvider, CustomerService customerService, RegisteredRepository registeredRepository) {
        this.authenticationManager = authenticationManager;
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.customerService = customerService;
        this.registeredRepository = registeredRepository;
    }

    @Override
    public LoginResponseDTO loginUser(LoginDTO loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
//        CustomerResponseDTO customer=customerService.findCustomerById(Integer.parseInt(loginDto.getUsername()));

        return new LoginResponseDTO(101,token);
    }

    @Override
    public int registerUser(CustomerDTO customerDTO) {
        Registered registeredUser = new Registered(customerDTO.getFirstName(),customerDTO.getLastName(),
                customerDTO.getNomineeName(),customerDTO.getAddress(),customerDTO.getEmail(),customerDTO.getUniqueIdentificationNumber(),
                customerDTO.getRole());
         registeredRepository.save(registeredUser);
        return 1;
    }
}
