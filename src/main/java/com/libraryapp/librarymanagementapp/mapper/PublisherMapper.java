package com.libraryapp.librarymanagementapp.mapper;

import com.libraryapp.librarymanagementapp.dto.publisher.request.CreatePublisherRequest;
import com.libraryapp.librarymanagementapp.dto.publisher.response.CreatedPublisherResponse;
import com.libraryapp.librarymanagementapp.dto.staff.response.CreatedStaffResponse;
import com.libraryapp.librarymanagementapp.entity.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);
    Publisher createPublisherRequestToPublisher(CreatePublisherRequest createPublisherRequest);
    CreatedPublisherResponse publisherToCreatePublisherResponse(Publisher publisher);
}
