package com.libraryapp.librarymanagementapp.repository;

import com.libraryapp.librarymanagementapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserNameContainingIgnoreCase(String userName);
}
