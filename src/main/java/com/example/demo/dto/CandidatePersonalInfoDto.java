package com.example.demo.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidatePersonalInfoDto {
    private Date dob;
    @NotEmpty
    private String gender;
    @NotEmpty
    private String address;
    @NotEmpty
    private String nationality;
}
