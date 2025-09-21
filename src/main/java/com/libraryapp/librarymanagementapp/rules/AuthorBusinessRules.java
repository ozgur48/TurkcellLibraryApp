package com.libraryapp.librarymanagementapp.rules;

import com.libraryapp.librarymanagementapp.dto.author.response.AuthorResponse;
import com.libraryapp.librarymanagementapp.entity.Author;
import com.libraryapp.librarymanagementapp.repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthorBusinessRules {
    private final AuthorRepository authorRepository;
    public AuthorBusinessRules(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public void authorMustNotExistWithSameNameAndLastName(String name,String lastName){
        Boolean exists = authorRepository.existsByNameIgnoreCaseAndLastNameIgnoreCase(name, lastName);
        if(exists) {
            if (exists) throw new RuntimeException("Bu yazar zaten kayıtlı");
        }
    }
    public Author mustExist(int id){
        return authorRepository.findById(id).orElseThrow(()->new RuntimeException("Bu ID'li Author bulunamadı: id->"+ id));
    }
}
