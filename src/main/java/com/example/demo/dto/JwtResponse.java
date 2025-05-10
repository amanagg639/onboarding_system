package com.example.demo.dto;

import com.example.demo.enums.RoleName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String userName;
    private String email;
    private RoleName role;

    public JwtResponse(String token, Long id, String userName, String email, RoleName role) {
        this.token = token;
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.role = role;
    }
}
