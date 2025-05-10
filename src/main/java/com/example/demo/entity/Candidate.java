package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.enums.OnboardingStatus;

import com.example.demo.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidate")
public class Candidate {
   @Id @GeneratedValue
   private long id;
   private String firstName;
   private String lastName;
   private String email;
   private String phoneNumber;

   @Enumerated(EnumType.STRING)
   private Status status;

   @Enumerated(EnumType.STRING)
   private OnboardingStatus onboardingStatus;
   
   private LocalDateTime createdAt;
   private LocalDateTime updatedAt;

   @OneToOne(mappedBy = "candidate")
   private CandidatePersonalInfo candidatePersonalInfo;

   @OneToOne(mappedBy = "candidate")
   private CandidateBankInfo candidateBankInfo;

   @OneToOne(mappedBy = "candidate")
   private CandidateEducation candidateEducation;

   @ManyToOne(cascade = CascadeType.ALL)
   private CandidateDocument candidateDocument;

   @PrePersist
   protected void onCreate() {
      createdAt = updatedAt = LocalDateTime.now();
      if (status == null) {
         status = Status.APPLIED;
      }
      if (onboardingStatus == null) {
         onboardingStatus = OnboardingStatus.NOT_STARTED;
      }
   }

   @PreUpdate
   protected void onUpdate() {
      updatedAt = LocalDateTime.now();
   }
}
