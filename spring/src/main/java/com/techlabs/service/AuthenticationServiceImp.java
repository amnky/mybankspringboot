package com.techlabs.service;

import com.techlabs.dto.CustomerDTO;
import com.techlabs.dto.LoginDTO;
import com.techlabs.dto.LoginResponseDTO;
import com.techlabs.entity.Registered;
import com.techlabs.exception.BadCredential;
import com.techlabs.repository.RegisteredRepository;
import com.techlabs.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

@Service
public class AuthenticationServiceImp implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailService emailService;
    private final RegisteredRepository registeredRepository;
    private static final String UPLOAD_DIR = "uploads/";

    public AuthenticationServiceImp(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                                    EmailService emailService, RegisteredRepository registeredRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.emailService = emailService;
        this.registeredRepository = registeredRepository;
    }

    @Override
    public LoginResponseDTO loginUser(LoginDTO loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(), loginDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateToken(authentication);
            boolean isCustomer = authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_CUSTOMER"));
            String userRole = "CUSTOMER";
            if (!isCustomer) userRole = "ADMIN";
            return new LoginResponseDTO(Integer.parseInt(loginDto.getUsername()), userRole, token);
        } catch (Exception e) {
            throw new BadCredential("wrong username or password");
        }
    }

    @Override
    public int registerUser(CustomerDTO customerDTO) throws IOException {
        Registered registeredUser = new Registered(customerDTO.getFirstName(), customerDTO.getLastName(),
                customerDTO.getNomineeName(), customerDTO.getAddress(), customerDTO.getEmail(),
                customerDTO.getUniqueIdentificationNumber());
        registeredUser = registeredRepository.save(registeredUser);
        emailService.sendEmailToAdmins(customerDTO);
        return registeredUser.getId();
    }

    @Override
    public void uploadandStorefile(int registeredId, int uniqueId, MultipartFile file) throws IOException {
        try {
            // Create the directory if it does not exist
            File directory = new File(UPLOAD_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Get the file's original filename
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String newFileName = uniqueId + fileExtension;
            System.out.println("new file name is:" + newFileName);
            // Create a Path where the file will be stored
            Path filePath = Paths.get(UPLOAD_DIR, newFileName);

            // Save the file to the specified path
            Files.write(filePath, file.getBytes());
            Registered registered=registeredRepository.findById(registeredId).
                    orElseThrow(()->new NoSuchElementException("user not found"));
            registered.setIsUploaded(true);
            registered.setDocExtension(fileExtension);
            registeredRepository.save(registered);

//            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed.");
        }
//        String path="C:/Users/yadav/Pictures/Camera Roll/customerdocs/"+file.getOriginalFilename();
//        Files.copy(file.getInputStream(), Path.of(path));
    }
}
