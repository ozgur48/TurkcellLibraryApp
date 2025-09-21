package com.libraryapp.librarymanagementapp.rules;

import com.libraryapp.librarymanagementapp.core.exception.type.BusinessException;
import com.libraryapp.librarymanagementapp.entity.User;
import com.libraryapp.librarymanagementapp.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserBusinessRules {
    private final UserRepository userRepository;

    public UserBusinessRules(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void userNameMustNotExistWithSameName(String userName){
        User user = userRepository.findByUserNameContainingIgnoreCase(userName).orElse(null);
        if(user != null){
            throw new BusinessException("Bu kullanıcı sistemde kayıtlı");
        }
    }
}
