package com.libraryapp.librarymanagementapp.dto.author.response;

import jakarta.validation.constraints.NotBlank;

public class CreatedAuthorResponse {

    private String name;

    private String lastName;

    public CreatedAuthorResponse(){}

    public CreatedAuthorResponse(String name, String lastName){
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
