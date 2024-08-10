package com.techlabs.dto;

import com.techlabs.utils.RoleType;
import jakarta.validation.constraints.NotBlank;

public class UpdateProfileDTO {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String nomineeName;
    @NotBlank
    private String address;
    @NotBlank
    private String email;

    public UpdateProfileDTO(String firstName, String lastName, String nomineeName, String address, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nomineeName = nomineeName;
        this.address = address;
        this.email = email;
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
}
