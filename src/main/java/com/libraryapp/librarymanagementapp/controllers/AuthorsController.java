package com.libraryapp.librarymanagementapp.controllers;

import com.libraryapp.librarymanagementapp.dto.author.request.CreateAuthorRequest;
import com.libraryapp.librarymanagementapp.dto.author.response.AuthorResponse;
import com.libraryapp.librarymanagementapp.dto.author.response.CreatedAuthorResponse;
import com.libraryapp.librarymanagementapp.entity.Author;
import com.libraryapp.librarymanagementapp.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Validated
@RestController
@RequestMapping("/api/library/authors")
public class AuthorsController {
    private AuthorService authorService;

    public AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreatedAuthorResponse> addAuthor(@Valid @RequestBody CreateAuthorRequest createAuthorRequest){
        CreatedAuthorResponse created = authorService.createAuthor(createAuthorRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> findAuthorById(@PathVariable int id){
        AuthorResponse response = authorService.findAuthorById(id);
        return ResponseEntity.ok(response);
    }

}
