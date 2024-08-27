package com.techlabs.entity;

import com.techlabs.utils.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "registered")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Registered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private int id;

    @Column(name = "first_name", nullable = false)
    @NotBlank
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    private String lastName;

    @Column(name = "nominee_name", length = 255)
    @NotBlank
    private String nomineeName;

    @Column(name = "address", length = 500)
    @NotBlank
    private String address;

    @Column(name="email")
    @NotBlank
    @Email
    private String email;
    @Column(name="unique_identification_number")
    @NotBlank
    private String uniqueIdentificationNumber;

    @Column(name="is_uploaded")
    private Boolean isUploaded;
    @Column(name="doc_extension")
    private String docExtension;

    public Registered( String firstName, String lastName, String nomineeName, String address,String email,String uniqueIdentificationNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nomineeName = nomineeName;
        this.address = address;
        this.email=email;
        this.uniqueIdentificationNumber=uniqueIdentificationNumber;
    }
}
