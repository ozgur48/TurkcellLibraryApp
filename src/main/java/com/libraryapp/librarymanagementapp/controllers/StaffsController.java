package com.libraryapp.librarymanagementapp.controllers;

import com.libraryapp.librarymanagementapp.service.StaffService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/library/staffs")
public class StaffsController {
    private final StaffService staffService;

    public StaffsController(StaffService staffService) {
        this.staffService = staffService;
    }

}
