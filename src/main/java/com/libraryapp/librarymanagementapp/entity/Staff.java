package com.libraryapp.librarymanagementapp.entity;

import com.libraryapp.librarymanagementapp.enums.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="staffs")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name="name")
    private String name;

    @NotBlank
    @Column(name="last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name="role_type", nullable = false)
    private RoleType roleType;

    public Staff(){}

    public Staff(int id, String name, String lastName, RoleType roleType) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.roleType = roleType;
    }

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
