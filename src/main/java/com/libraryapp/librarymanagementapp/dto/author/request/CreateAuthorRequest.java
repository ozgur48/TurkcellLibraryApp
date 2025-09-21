package com.libraryapp.librarymanagementapp.dto.author.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public class CreateAuthorRequest {
    @NotBlank
    private String name;
    @NotBlank
    @JsonAlias("last_name")
    private String lastName;

    public String getName() {
        return name;
    }
    public CreateAuthorRequest(){}

    public CreateAuthorRequest(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
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
