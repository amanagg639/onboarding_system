package com.example.demo.dto;

import com.example.demo.entity.Candidate;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobOfferDto {

    private Boolean sent;

    private LocalDateTime sentAt;

    private Integer retryCount;

    private String errorMessage;

}
