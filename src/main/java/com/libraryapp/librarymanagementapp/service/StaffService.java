package com.libraryapp.librarymanagementapp.service;


import com.libraryapp.librarymanagementapp.dto.staff.request.CreateStaffRequest;
import com.libraryapp.librarymanagementapp.dto.staff.request.UpdateStaffRequest;
import com.libraryapp.librarymanagementapp.dto.staff.response.*;
import com.libraryapp.librarymanagementapp.entity.Staff;
import com.libraryapp.librarymanagementapp.mapper.StaffMapper;
import com.libraryapp.librarymanagementapp.repository.StaffRepository;
import com.libraryapp.librarymanagementapp.rules.StaffBusinessRules;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {
    private final StaffRepository staffRepository;
    private final StaffBusinessRules staffBusinessRules;
    private final StaffMapper staffMapper;

    public StaffService(StaffRepository staffRepository, StaffBusinessRules staffBusinessRules, StaffMapper staffMapper) {
        this.staffRepository = staffRepository;
        this.staffBusinessRules = staffBusinessRules;
        this.staffMapper = staffMapper;
    }

    public GetByIdStaffResponse getById(int id){
        Staff staff = staffBusinessRules.mustExist(id);
        return staffMapper.toGetByIdStaffResponse(staff);
    }
    public List<GetListStaffResponse> getStaffList(){
        List<Staff> staffs = staffRepository.findAll();
        return staffMapper.toGetListStaffResponse(staffs);
    }

    public CreatedStaffResponse addStaff(CreateStaffRequest createStaffRequest){
        staffBusinessRules.staffShouldNotExistWithSameNameandLastName(createStaffRequest.getName(), createStaffRequest.getLastName());
        Staff staff = staffMapper.toStaff(createStaffRequest);
        return staffMapper.toCreatedStaffResponse(staff);
    }


    public UptadedStaffResponse updateStaff(UpdateStaffRequest updateStaffRequest){
        staffBusinessRules.mustExist(updateStaffRequest.getId());

        Staff staff = staffMapper.toUpdateStaff(updateStaffRequest);
        staff = staffRepository.save(staff);
        return staffMapper.toUpdateStaffResponse(staff);
    }
    /* public DeletedStaffResponse deleteStaff(int id){
        Staff staff = staffBusinessRules.mustExist(id);
        staffRepository.delete(staff);
    } */


}
