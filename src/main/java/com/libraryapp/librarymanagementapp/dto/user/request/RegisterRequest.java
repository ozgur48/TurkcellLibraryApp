package com.libraryapp.librarymanagementapp.dto.user.request;

public class RegisterRequest {

    private String userName;
    private String password;
    private String email;

    public RegisterRequest(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    public RegisterRequest(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
