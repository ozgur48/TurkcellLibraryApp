package com.libraryapp.librarymanagementapp.dto.member.request;

import com.libraryapp.librarymanagementapp.enums.MemberShipLevel;
import com.libraryapp.librarymanagementapp.enums.MemberStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UpdateMemberByIdRequest {
    private int id;
    @NotBlank
    private String name;

    public int getId() {
        return id;
    }

    public UpdateMemberByIdRequest(int id, String name, MemberStatus memberStatus) {
        this.id = id;
        this.name = name;
        this.memberStatus = memberStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MemberStatus getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    private MemberStatus memberStatus;

}
