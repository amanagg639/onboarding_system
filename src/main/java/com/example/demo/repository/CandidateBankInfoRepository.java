package com.example.demo.repository;

import com.example.demo.entity.CandidateBankInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateBankInfoRepository extends JpaRepository<CandidateBankInfo, Long> {
}
