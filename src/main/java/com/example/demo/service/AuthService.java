package com.example.demo.service;

import com.example.demo.dto.SignupRequest;
import com.example.demo.entity.Users;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserDetailsRepository;
import com.example.demo.util.OtpJwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    OtpJwtUtil otpJwtUtil;

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

    public void sendOtp(String email){
        Users users = userDetailsRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException(email));
        String otp = String.valueOf((int)(Math.random()*900000)+100000);
        users.setOtp(otp);
        users.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5));
        userDetailsRepository.save(users);
        emailService.sendOtpEmail(email, otp);
    }

    public String verifyOtp(String email, String otp){
        Optional<Users> user = userDetailsRepository.findByEmailAndOtp(email, otp);
        if(user.isPresent() && user.get().getOtpExpiryTime().isAfter(LocalDateTime.now())){
            return otpJwtUtil.generateToken(email, 10);
        }
        throw new RuntimeException("OTP is invalid or has expired!");

    }

    public void updatePassword(String token, String password){
        String email;
        try{
            email = otpJwtUtil.validateAndGetEmail(token);
        } catch (Exception e){
            throw new RuntimeException("Invalid token!");
        }

        Users users = userDetailsRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException(email));
        users.setPassword(encoder.encode(password));
        users.setOtp(null);
        users.setOtpExpiryTime(null);
        userDetailsRepository.save(users);
    }
}
