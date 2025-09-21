package com.libraryapp.librarymanagementapp.repository;

import com.libraryapp.librarymanagementapp.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    boolean existsByNameIgnoreCase(String name);
    Optional<Member> findByName(String name);
    Optional<Member> findByEmail(String email);
    Optional<Member> findById(int id);
}
