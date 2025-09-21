package com.libraryapp.librarymanagementapp.service;

import com.libraryapp.librarymanagementapp.dto.book.request.CreateBookRequest;
import com.libraryapp.librarymanagementapp.dto.publisher.request.CreatePublisherRequest;
import com.libraryapp.librarymanagementapp.dto.publisher.response.CreatedPublisherResponse;
import com.libraryapp.librarymanagementapp.entity.Publisher;
import com.libraryapp.librarymanagementapp.mapper.PublisherMapper;
import com.libraryapp.librarymanagementapp.repository.PublisherRepository;
import com.libraryapp.librarymanagementapp.rules.PublisherBusinessRules;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherBusinessRules publisherBusinessRules;
    private final PublisherMapper publisherMapper;

    public PublisherService(PublisherRepository publisherRepository, PublisherBusinessRules publisherBusinessRules, PublisherMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherBusinessRules = publisherBusinessRules;
        this.publisherMapper = publisherMapper;
    }

    public CreatedPublisherResponse addPublisher(CreatePublisherRequest createPublisherRequest){
        publisherBusinessRules.publisherMustNotExistWithSameName(createPublisherRequest.getName());
        Publisher publisher = publisherMapper.createPublisherRequestToPublisher(createPublisherRequest);
        publisher = publisherRepository.save(publisher);
        return publisherMapper.publisherToCreatePublisherResponse(publisher);
    }
}
