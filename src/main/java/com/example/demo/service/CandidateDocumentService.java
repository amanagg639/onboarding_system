package com.example.demo.service;

import com.example.demo.entity.Candidate;
import com.example.demo.entity.CandidateDocument;
import com.example.demo.exception.CandidateDocumentNotFoundException;
import com.example.demo.exception.CandidateNotFoundException;
import com.example.demo.repository.CandidateDocumentRepository;
import com.example.demo.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CandidateDocumentService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CandidateDocumentRepository candidateDocumentRepository;

    public void uploadDocument(long candidateId, String documentType, MultipartFile file) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(
                () -> new CandidateNotFoundException(candidateId));

        try {
            CandidateDocument candidateDocument = new CandidateDocument();
            candidateDocument.setCandidate(candidate);
            candidateDocument.setDocumentType(documentType);
            candidateDocument.setFileData(file.getBytes());
            candidateDocument.setIsVerified(false);
            candidateDocumentRepository.save(candidateDocument);
        }catch (IOException e){
            throw new RuntimeException("Error uploading file: " + file.getOriginalFilename(), e);
        }
    }

    public void verifyDocument(Long candidateId, Long documentId) {
        CandidateDocument candidateDocument = candidateDocumentRepository.findById(documentId)
                .orElseThrow(() -> new CandidateDocumentNotFoundException(candidateId, documentId));

        candidateDocument.setIsVerified(true);
        candidateDocumentRepository.save(candidateDocument);
    }
}
