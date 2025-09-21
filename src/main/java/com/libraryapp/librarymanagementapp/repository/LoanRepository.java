package com.libraryapp.librarymanagementapp.repository;

import com.libraryapp.librarymanagementapp.entity.Book;
import com.libraryapp.librarymanagementapp.entity.Loan;
import com.libraryapp.librarymanagementapp.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    long countByMemberAndReturnDateIsNull(Member member);
    Optional<Loan> findByMemberIdAndBookIsbnAndReturnDateIsNull(int memberId, String isbn);
    Optional<Loan> findById(int id);
}
