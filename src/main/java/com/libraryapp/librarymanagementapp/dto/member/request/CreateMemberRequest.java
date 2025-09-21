package com.libraryapp.librarymanagementapp.dto.member.request;

import com.libraryapp.librarymanagementapp.enums.MemberShipLevel;
import com.libraryapp.librarymanagementapp.enums.MemberStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateMemberRequest {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    // TR mobil numara: +905xxxxxxxxx veya 05xxxxxxxxx kabul eder
    @Pattern(
            regexp = "^(?:\\+90|0)?5\\d{9}$",
            message = "Telefon numarası geçersiz. Örn: +9053XXXXXXX veya 05XXXXXXXXX"
    )
    private String phone;

    @NotBlank
    private String address;


    private MemberShipLevel memberShipLevel;

    private MemberStatus memberStatus;

    public CreateMemberRequest(String name, String email, String phone, String address, MemberShipLevel memberShipLevel, MemberStatus memberStatus) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.memberShipLevel = memberShipLevel;
        this.memberStatus = memberStatus;
    }
    public CreateMemberRequest(){}

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
