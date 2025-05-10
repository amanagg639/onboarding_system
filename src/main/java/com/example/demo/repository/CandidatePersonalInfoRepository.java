package com.example.demo.repository;

import com.example.demo.entity.CandidatePersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatePersonalInfoRepository extends JpaRepository<CandidatePersonalInfo, Long>{
    CandidatePersonalInfo findByCandidateId(Long candidateId);
}
