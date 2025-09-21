package com.libraryapp.librarymanagementapp.repository;

import com.libraryapp.librarymanagementapp.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    Optional<Publisher> findByNameContainingIgnoreCase(String name);
}
