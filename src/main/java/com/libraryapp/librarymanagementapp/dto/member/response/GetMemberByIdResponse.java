package com.libraryapp.librarymanagementapp.dto.member.response;

import com.libraryapp.librarymanagementapp.enums.MemberShipLevel;
import com.libraryapp.librarymanagementapp.enums.MemberStatus;

public class GetMemberByIdResponse {
    private String name;

    private String email;

    private String phone;

    private String address;

    private MemberShipLevel memberShipLevel;

    private MemberStatus memberStatus;

    public GetMemberByIdResponse(String name, String email, String phone, String address, MemberShipLevel memberShipLevel, MemberStatus memberStatus) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.memberShipLevel = memberShipLevel;
        this.memberStatus = memberStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MemberShipLevel getMemberShipLevel() {
        return memberShipLevel;
    }

    public void setMemberShipLevel(MemberShipLevel memberShipLevel) {
        this.memberShipLevel = memberShipLevel;
    }

    public MemberStatus getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }


}
