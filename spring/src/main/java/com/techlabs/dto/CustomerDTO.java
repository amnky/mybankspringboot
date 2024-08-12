package com.techlabs.dto;

import com.techlabs.utils.RoleType;
import jakarta.validation.constraints.NotBlank;

public class CustomerDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
    private String nomineeName;
    private String address;
    private String email;
    private String uniqueIdentificationNumber;

    public CustomerDTO() {
    }


    public CustomerDTO(String firstName, String lastName, String nomineeName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nomineeName = nomineeName;
        this.address = address;
    }

    public CustomerDTO(String firstName, String lastName, String nomineeName, String address, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nomineeName = nomineeName;
        this.address = address;
        this.email = email;
    }

    public CustomerDTO(String firstName, String lastName, String nomineeName, String address, String email,
                       String uniqueIdentificationNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nomineeName = nomineeName;
        this.address = address;
        this.email = email;
        this.uniqueIdentificationNumber = uniqueIdentificationNumber;
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
