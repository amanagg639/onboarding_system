package com.example.demo.repository;

import com.example.demo.entity.CandidateDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateDocumentRepository extends JpaRepository<CandidateDocument, Long> {
}
