package com.libraryapp.librarymanagementapp.mapper;

import com.libraryapp.librarymanagementapp.dto.staff.request.CreateStaffRequest;
import com.libraryapp.librarymanagementapp.dto.staff.request.UpdateStaffRequest;
import com.libraryapp.librarymanagementapp.dto.staff.response.CreatedStaffResponse;
import com.libraryapp.librarymanagementapp.dto.staff.response.GetByIdStaffResponse;
import com.libraryapp.librarymanagementapp.dto.staff.response.GetListStaffResponse;
import com.libraryapp.librarymanagementapp.dto.staff.response.UptadedStaffResponse;
import com.libraryapp.librarymanagementapp.entity.Staff;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    List<GetListStaffResponse> toGetListStaffResponse(List<Staff> staffList);
    GetByIdStaffResponse toGetByIdStaffResponse(Staff staff);
    Staff toStaff(CreateStaffRequest createStaffRequest);
    CreatedStaffResponse toCreatedStaffResponse(Staff staff);
    Staff toUpdateStaff(UpdateStaffRequest updateStaffRequest);
    UptadedStaffResponse toUpdateStaffResponse(Staff staff);
}
