package com.libraryapp.librarymanagementapp.service;

import com.libraryapp.librarymanagementapp.dto.author.request.CreateAuthorRequest;
import com.libraryapp.librarymanagementapp.dto.author.response.AuthorResponse;
import com.libraryapp.librarymanagementapp.dto.author.response.CreatedAuthorResponse;
import com.libraryapp.librarymanagementapp.entity.Author;
import com.libraryapp.librarymanagementapp.mapper.AuthorMapper;
import com.libraryapp.librarymanagementapp.repository.AuthorRepository;
import com.libraryapp.librarymanagementapp.rules.AuthorBusinessRules;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorBusinessRules authorBusinessRules;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorBusinessRules authorBusinessRules){
        this.authorRepository = authorRepository;
        this.authorBusinessRules = authorBusinessRules;
        this.authorMapper = AuthorMapper.INSTANCE;
    }

    public CreatedAuthorResponse createAuthor(CreateAuthorRequest createAuthorRequest)
    {
        authorBusinessRules.authorMustNotExistWithSameNameAndLastName(createAuthorRequest.getName(),createAuthorRequest.getLastName());

        Author author = new Author();
        author.setName(createAuthorRequest.getName());
        author.setLastName(createAuthorRequest.getLastName());
        authorRepository.save(author);

        return new CreatedAuthorResponse(author.getName(), author.getLastName());
    }
    public AuthorResponse findAuthorById(int id){
        Author author = authorBusinessRules.mustExist(id);
        return new AuthorResponse(author.getName(), author.getLastName());
    }
}
