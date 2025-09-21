package com.libraryapp.librarymanagementapp.controllers;

import com.libraryapp.librarymanagementapp.dto.publisher.request.CreatePublisherRequest;
import com.libraryapp.librarymanagementapp.dto.publisher.response.CreatedPublisherResponse;
import com.libraryapp.librarymanagementapp.service.PublisherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@RequestMapping("/api/library/publisher")

public class PublishersController {
    private PublisherService publisherService;

    public PublishersController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    public ResponseEntity<CreatedPublisherResponse> addPublisher(@Valid @RequestBody CreatePublisherRequest createPublisherRequest){
        CreatedPublisherResponse res = publisherService.addPublisher(createPublisherRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}
