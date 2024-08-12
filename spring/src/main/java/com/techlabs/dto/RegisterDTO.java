package com.techlabs.dto;

import com.techlabs.utils.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegisterDTO {

    private int id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String nomineeName;
    @NotBlank
    private String address;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String uniqueIdentificationNumber;

    public RegisterDTO(int id, String firstName, String lastName, String nomineeName, String address,
                       String email, String uniqueIdentificationNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nomineeName = nomineeName;
        this.address = address;
        this.email = email;
        this.uniqueIdentificationNumber = uniqueIdentificationNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNomineeName() {
        return nomineeName;
    }

    public void setNomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniqueIdentificationNumber() {
        return uniqueIdentificationNumber;
    }

    public void setUniqueIdentificationNumber(String uniqueIdentificationNumber) {
        this.uniqueIdentificationNumber = uniqueIdentificationNumber;
    }
}
