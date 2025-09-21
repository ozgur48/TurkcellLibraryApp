package com.libraryapp.librarymanagementapp.dto.author.response;

public class AuthorResponse {

    private String name;

    private String lastName;

    public AuthorResponse(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
