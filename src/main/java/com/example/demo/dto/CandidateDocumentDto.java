package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDocumentDto {
    private long candidateId;
    private String fileName;
    private String documentType;
    private Boolean isVerified;

}
