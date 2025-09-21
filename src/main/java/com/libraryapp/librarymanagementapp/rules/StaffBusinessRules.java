package com.libraryapp.librarymanagementapp.rules;

import com.libraryapp.librarymanagementapp.core.exception.type.BusinessException;
import com.libraryapp.librarymanagementapp.entity.Staff;
import com.libraryapp.librarymanagementapp.repository.StaffRepository;
import org.springframework.stereotype.Component;

@Component
public class StaffBusinessRules {
    private final StaffRepository staffRepository;

    public StaffBusinessRules(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public Staff mustExist(int id){
        return staffRepository.findById(id).orElseThrow(()-> new BusinessException("Bu isimde staff zaten kayıtlı"));
    }
    public void staffShouldNotExistWithSameNameandLastName(String name, String lastName){
        Staff staff = staffRepository.findByNameAndLastNameIsContainingIgnoreCase(name, lastName).orElse(null);
        if(staff != null){
            throw new BusinessException("Bu " + name + "ve soyisimli" + lastName + " staff sistemde kayıtlı");
        }

    }
}
