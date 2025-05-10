package com.example.demo.service;

import com.example.demo.dto.SignupRequest;
import com.example.demo.entity.Users;
import com.example.demo.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    PasswordEncoder encoder;

    public boolean existsByUserName(String userName){
        return userDetailsRepository.findByUserName(userName).isPresent();
    }

    public void registerUser(SignupRequest signupRequest){
        Users users = new Users();
        users.setUserName(signupRequest.getUserName());
        users.setPassword(encoder.encode(signupRequest.getPassword()));
        users.setEmail(signupRequest.getEmail());
        users.setRole(signupRequest.getRole());
        userDetailsRepository.save(users);
    }
}
