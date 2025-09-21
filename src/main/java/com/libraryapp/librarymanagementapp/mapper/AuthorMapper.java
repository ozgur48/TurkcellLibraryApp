package com.libraryapp.librarymanagementapp.mapper;

import com.libraryapp.librarymanagementapp.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")

public interface AuthorMapper {
    // dışarıdan bu mappere ulasmayı sağlar
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);
}
