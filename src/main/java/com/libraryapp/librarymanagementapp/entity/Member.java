package com.libraryapp.librarymanagementapp.entity;

import com.libraryapp.librarymanagementapp.enums.MemberShipLevel;
import com.libraryapp.librarymanagementapp.enums.MemberStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Entity
@Table(name="members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    @Column(name="email", nullable = false, unique = true)
    private String email;


    @Column(length = 13, nullable = false)
    private String phone;

    @NotBlank
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name="member_ship_level", nullable = false)
    private MemberShipLevel memberShipLevel;

    @Enumerated(EnumType.STRING)
    @Column(name="member_status", nullable = false)
    private MemberStatus memberStatus;

    @OneToMany(mappedBy = "member")

    private List<Fine> fines;
    public List<Fine> getFines() {
        return fines;
    }

    public void setFines(List<Fine> fines) {
        this.fines = fines;
    }

    public Member(int id, String name, String email, String phone, String address, MemberShipLevel memberShipLevel, MemberStatus status, List<Fine> fines) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.memberShipLevel = memberShipLevel;
        this.memberStatus = memberStatus;
        this.fines = fines;
    }
    public Member(){}

    public int getId() {
        return id;
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
