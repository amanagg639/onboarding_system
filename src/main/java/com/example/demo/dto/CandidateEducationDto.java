package com.example.demo.dto;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateEducationDto {
    @NotEmpty
    private String degree;
    @NotEmpty
    private String institution;
    @NotEmpty
    private String yearOfGraduation;
}
