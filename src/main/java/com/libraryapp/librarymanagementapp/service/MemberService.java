package com.libraryapp.librarymanagementapp.service;

import com.libraryapp.librarymanagementapp.dto.member.request.CreateMemberRequest;
import com.libraryapp.librarymanagementapp.dto.member.request.UpdateMemberByIdRequest;
import com.libraryapp.librarymanagementapp.dto.member.response.*;
import com.libraryapp.librarymanagementapp.entity.Fine;
import com.libraryapp.librarymanagementapp.entity.Member;
import com.libraryapp.librarymanagementapp.enums.MemberStatus;
import com.libraryapp.librarymanagementapp.mapper.MemberMapper;
import com.libraryapp.librarymanagementapp.repository.FineRepository;
import com.libraryapp.librarymanagementapp.repository.MemberRepository;
import com.libraryapp.librarymanagementapp.rules.MemberBusinessRules;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberBusinessRules memberBusinessRules;
    private final MemberMapper memberMapper;
    private final FineRepository fineRepository;

    public MemberService(MemberRepository memberRepository, MemberBusinessRules memberBusinessRules, FineRepository fineRepository) {
        this.memberRepository = memberRepository;
        this.memberBusinessRules = memberBusinessRules;
        this.fineRepository = fineRepository;
        this.memberMapper = MemberMapper.INSTANCE;
    }
    // create member
    public CreatedMemberResponse createMember(CreateMemberRequest createMemberRequest){
        //memberBusinessRules.MemberIsNotExistWithSameName(createMemberRequest.getName());
        memberBusinessRules.MemberMustNotWithSameName(createMemberRequest.getName());
        memberBusinessRules.checkIfMemberEmailIsUnique(createMemberRequest.getEmail());

        Member member = memberMapper.createMemberRequestToMember(createMemberRequest);
        member = memberRepository.save(member);

        return memberMapper.memberToCreateResponseMember(member);
    }

    // member findById
    public GetMemberByIdResponse findMemberById(int id){
        Member member = memberBusinessRules.memberMustExistWithGivenId(id);
        return memberMapper.toGetByIdMemberResponse(member);

    }
    public GetMemberByStatusAndEmailResponse findMemberByStatusAndEmail(MemberStatus memberStatus, String email){
        Member member = new Member();
        member.setMemberStatus(memberStatus);
        member.setEmail(email);
        if(member.getMemberStatus() == MemberStatus.ACTIVE && member.getEmail().equals(email)){
           return memberMapper.toGetByStatusAndEmailResponse(member);
        }
        else{
            return null;
        }
    }
    public UpdatedMemberByIdResponse updateMemberById(int id, MemberStatus status){
        Member member = memberBusinessRules.memberMustExistWithGivenId(id);
        if(member.getMemberStatus() != status ){
            member.setMemberStatus(status);
            memberRepository.save(member);
        }
        return memberMapper.memberToUpdatedMemberByIdResponse(member);
    }

    public List<GetFineListByIdResponse> getFineListByIdResponse(int id, boolean isPaid){
        Member member = memberBusinessRules.memberMustExistWithGivenId(id);
        List<Fine> fines = (!isPaid)
                ? fineRepository.findByMemberId(id)
                : fineRepository.findByMemberIdAndIsPaid(id, isPaid);

        if (fines == null) fines = Collections.emptyList();

        return memberMapper.toGetFineListByIdResponse(fines);
    }



}
