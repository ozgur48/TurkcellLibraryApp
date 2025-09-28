package com.libraryapp.librarymanagementapp.rules;

import com.libraryapp.librarymanagementapp.core.exception.type.BusinessException;
import com.libraryapp.librarymanagementapp.dto.user.response.LoginResponse;
import com.libraryapp.librarymanagementapp.entity.Book;
import com.libraryapp.librarymanagementapp.entity.User;
import com.libraryapp.librarymanagementapp.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserBusinessRules {
    private final UserRepository userRepository;

    public UserBusinessRules(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User userNameMustNotExistWithSameName(String userName){
        User user = userRepository.findByUserNameContainingIgnoreCase(userName).orElse(null);
        if(user !=null){
            throw new BusinessException("Bu kullanıcı sistemde kayıtlı");
        }
        return user;
    }
    public void checkUserName(String userName){
        userRepository.findByUserNameContainingIgnoreCase(userName).orElseThrow(()-> new BusinessException("Kullanıcı adı ve şifre hatalı"));
    }
}
