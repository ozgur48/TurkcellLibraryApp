package com.libraryapp.librarymanagementapp.repository;

import com.libraryapp.librarymanagementapp.entity.Fine;
import com.libraryapp.librarymanagementapp.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FineRepository extends JpaRepository<Fine, Integer> {
    boolean existsByMemberAndIsPaidFalse(Member member);

    List<Fine> findByMemberId(int memberId);
    List<Fine> findByMemberIdAndIsPaid(int memberId, boolean isPaid);

}
