package com.libraryapp.librarymanagementapp.service;

import com.libraryapp.librarymanagementapp.core.exception.type.BusinessException;
import com.libraryapp.librarymanagementapp.core.jwt.JwtUtil;
import com.libraryapp.librarymanagementapp.dto.user.request.LoginRequest;
import com.libraryapp.librarymanagementapp.dto.user.request.RegisterRequest;
import com.libraryapp.librarymanagementapp.dto.user.response.LoginResponse;
import com.libraryapp.librarymanagementapp.dto.user.response.RegisteredResponse;
import com.libraryapp.librarymanagementapp.entity.User;
import com.libraryapp.librarymanagementapp.mapper.UserMapper;
import com.libraryapp.librarymanagementapp.repository.UserRepository;
import com.libraryapp.librarymanagementapp.rules.UserBusinessRules;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final UserBusinessRules userBusinessRules;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, UserBusinessRules userBusinessRules, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.userBusinessRules = userBusinessRules;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userMapper = UserMapper.INSTANCE;
    }

    public RegisteredResponse register(RegisterRequest registerRequest){

        userBusinessRules.userNameMustNotExistWithSameName(registerRequest.getUserName());

        // mapper içinde password encode ettik.
        User user = userMapper.registerRequestToUser(passwordEncoder, registerRequest);

        //user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user = userRepository.save(user);

        return userMapper.userToRegisterResponse(user);
    }

    public LoginResponse login(LoginRequest loginRequest){
        User user = userRepository.findByUserNameContainingIgnoreCase(loginRequest.getUserName()).orElseThrow(()-> new BusinessException("Kullanıcı adı ve şifre hatalı"));
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new BusinessException("Wrong username or password");
        }
        LoginResponse res = new LoginResponse();
        res.setToken(jwtUtil.generateToken(user.getUserName()));
        return res;
    }





}
