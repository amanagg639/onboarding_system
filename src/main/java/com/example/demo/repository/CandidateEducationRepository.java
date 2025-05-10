package com.example.demo.repository;

import com.example.demo.entity.CandidateEducation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateEducationRepository extends JpaRepository<CandidateEducation, Long> {
    CandidateEducation findByCandidateId(Long candidateId);
}
