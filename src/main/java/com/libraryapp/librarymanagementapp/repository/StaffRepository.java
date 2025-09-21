package com.libraryapp.librarymanagementapp.repository;

import com.libraryapp.librarymanagementapp.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

    Optional<Staff> findByNameAndLastNameIsContainingIgnoreCase(String name, String lastName);
}
