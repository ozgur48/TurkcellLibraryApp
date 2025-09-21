package com.libraryapp.librarymanagementapp.rules;

import com.libraryapp.librarymanagementapp.core.exception.type.BusinessException;
import com.libraryapp.librarymanagementapp.entity.Publisher;
import com.libraryapp.librarymanagementapp.repository.PublisherRepository;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

@Component
public class PublisherBusinessRules {
    private final PublisherRepository publisherRepository;

    public PublisherBusinessRules (PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }
    public void publisherMustNotExistWithSameName(String name){
        Publisher publisherWithSameName = publisherRepository.findByNameContainingIgnoreCase(name).orElse(null);
        if(publisherWithSameName != null){
            throw new BusinessException("Bu publisher sistemde bulunmaktadır.");
        }
    }


}
