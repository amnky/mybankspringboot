package com.techlabs.dto;

public class LoginResponseDTO {

    private int customerId;
    private String cookie;

    public LoginResponseDTO(int customerId, String cookie) {
        this.customerId = customerId;
        this.cookie = cookie;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
