package com.techlabs.dto;

import com.techlabs.utils.RoleType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

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
    @NotBlank
    private String uniqueIdentificationNumber;

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
}
