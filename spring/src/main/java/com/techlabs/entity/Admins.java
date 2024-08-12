package com.techlabs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "admin")
public class Admins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "admin_id")
    private int adminId;
    @NotBlank
    @Column(name = "admin_username")
    private String adminUsername;
    @NotBlank
    @Email
    @Column(name = "admin_email")
    private String adminEmail;
    @NotBlank
    @Column(name = "admin_password")
    private String adminPassword;

    public Admins() {
    }

    public Admins(int adminId, String adminUsername, String adminEmail, String adminPassword) {
        this.adminId = adminId;
        this.adminUsername = adminUsername;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    @Override
    public String toString() {
        return "Admins{" +
                "adminId=" + adminId +
                ", adminUsername='" + adminUsername + '\'' +
                ", adminEmail='" + adminEmail + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                '}';
    }
}
