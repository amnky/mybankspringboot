package com.techlabs.entity;

import com.techlabs.utils.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "registered")
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

    @Column(name="user_role")
    @NotBlank
    private RoleType roleType;

    public Registered() {
    }
    public Registered( String firstName, String lastName, String nomineeName, String address,String email,String uniqueIdentificationNumber,RoleType roleType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nomineeName = nomineeName;
        this.address = address;
        this.email=email;
        this.uniqueIdentificationNumber=uniqueIdentificationNumber;
        this.roleType=roleType;
    }

    public Registered(int id, String firstName, String lastName, String nomineeName, String address,String email,String uniqueIdentificationNumber,RoleType roleType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nomineeName = nomineeName;
        this.address = address;
        this.email=email;
        this.uniqueIdentificationNumber=uniqueIdentificationNumber;
        this.roleType=roleType;
    }
    // Getters and Setters

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

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    @Override
    public String toString() {
        return "Registered{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nomineeName='" + nomineeName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", uniqueIdentificationNumber='" + uniqueIdentificationNumber + '\'' +
                ", roleType=" + roleType +
                '}';
    }
}
