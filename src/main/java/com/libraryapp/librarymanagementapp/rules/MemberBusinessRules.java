package com.libraryapp.librarymanagementapp.rules;

import com.libraryapp.librarymanagementapp.core.exception.type.BusinessException;
import com.libraryapp.librarymanagementapp.entity.Member;
import com.libraryapp.librarymanagementapp.enums.MemberShipLevel;
import com.libraryapp.librarymanagementapp.enums.MemberStatus;
import com.libraryapp.librarymanagementapp.repository.FineRepository;
import com.libraryapp.librarymanagementapp.repository.LoanRepository;
import com.libraryapp.librarymanagementapp.repository.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class MemberBusinessRules {
    private final MemberRepository memberRepository;
    private final LoanRepository loanRepository;
    private final FineRepository fineRepository;

    public MemberBusinessRules(MemberRepository memberRepository, LoanRepository loanRepository, FineRepository fineRepository) {
        this.memberRepository = memberRepository;
        this.loanRepository = loanRepository;
        this.fineRepository = fineRepository;
    }

    public void MemberIsNotExistWithSameName(String name){
        Boolean  exists = memberRepository.existsByNameIgnoreCase(name);
        if(exists) {
            if (exists) throw new BusinessException("Bu member zaten kayıtlı");
        }
    }
    public void MemberMustNotWithSameName(String name){
        Member member = memberRepository.findByName(name).orElse(null);
        if(member != null){
            throw new BusinessException("Bu isimde kullanıcı sistemde kayıtlı");
        }
    }
    public void checkIfMemberEmailIsUnique(String email){
        Member member = memberRepository.findByEmail(email).orElse(null);
        if(member != null){
            throw new BusinessException("Farklı mail adresi kullanınız.");
        }
    }
    public void checkMemberShipLoanLimit(Member member){
        long activeLoans = loanRepository.countByMemberAndReturnDateIsNull(member);
        if(member.getMemberShipLevel()== MemberShipLevel.STANDART && (activeLoans >= 3) ){
            throw new BusinessException("Standart üyenin ödünç kitap alma sayısı 3'ten fazla olamaz !!!");
        }
        if(member.getMemberShipLevel() ==MemberShipLevel.GOLD && (activeLoans >= 5)){
            throw new BusinessException("Gold üyenin ödünç kitap alma sayısı 5'ten fazla olamaz !!!");
        }
    }

    public void checkMemberStatus(Member member){
        if(member.getMemberStatus().equals(MemberStatus.BANNED)){
            throw new BusinessException("Bu kullanıcı kitap ödünç alamaz." + MemberStatus.BANNED.toString());
        }
        if(member.getMemberStatus().equals(MemberStatus.INACTIVE)){
            throw new BusinessException("Bu kullanıcı inaktif, kitap ödünç almak için hesabı aktifleştiriniz." + MemberStatus.INACTIVE.toString());
        }
    }

    public void checkIfHasUnpaidFines(Member member){
        boolean hasUnpaid = fineRepository.existsByMemberAndIsPaidFalse(member);
        if(hasUnpaid){
            throw new BusinessException("Bu kullanıcının ödenmemiş borcu bulunmaktadır.");
        }
    }
    public Member memberMustExistWithGivenId(int id){
        return memberRepository.findById(id).orElseThrow(() -> new BusinessException("member with id " + id + "not found"));
    }


}
