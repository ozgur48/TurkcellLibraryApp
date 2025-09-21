package com.libraryapp.librarymanagementapp.controllers;

import com.libraryapp.librarymanagementapp.dto.book.response.CreatedBookResponse;
import com.libraryapp.librarymanagementapp.dto.member.request.CreateMemberRequest;
import com.libraryapp.librarymanagementapp.dto.member.request.UpdateMemberByIdRequest;
import com.libraryapp.librarymanagementapp.dto.member.response.CreatedMemberResponse;
import com.libraryapp.librarymanagementapp.dto.member.response.GetFineListByIdResponse;
import com.libraryapp.librarymanagementapp.dto.member.response.GetMemberByIdResponse;
import com.libraryapp.librarymanagementapp.dto.member.response.UpdatedMemberByIdResponse;
import com.libraryapp.librarymanagementapp.entity.Member;
import com.libraryapp.librarymanagementapp.enums.MemberStatus;
import com.libraryapp.librarymanagementapp.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/library/members")
public class MembersController {
    private final MemberService memberService;

    public MembersController(MemberService memberService) {
        this.memberService = memberService;
    }
    @PostMapping
    public ResponseEntity<CreatedMemberResponse> createMember(@RequestBody @Valid CreateMemberRequest createMemberRequest){
        CreatedMemberResponse m =  memberService.createMember(createMemberRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(m);
    }
    @GetMapping("/{id}")
    public GetMemberByIdResponse findById(@Valid @PathVariable int id){
        return memberService.findMemberById(id);
    }
    @PatchMapping("{id}/status")
    public UpdatedMemberByIdResponse uptadedStatusById(@Valid @PathVariable int id, @RequestParam MemberStatus memberStatus){
        return memberService.updateMemberById(id, memberStatus);
    }

    // GET /api/members/{id}/fines?isPaid=false
    @GetMapping("/{id}/fines")
    public List<GetFineListByIdResponse> listFines(
            @PathVariable int id,
            @RequestParam(required = false) Boolean isPaid
    ) {
        return memberService.getFineListByIdResponse(id, isPaid);
    }
}
