package com.example.demo.controller;

import com.example.demo.dto.CandidateDocumentDto;
import com.example.demo.dto.MessageResponse;
import com.example.demo.service.CandidateDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/candidate/{candidateId}/document")
public class CandidateDocumentController {

    @Autowired
    private CandidateDocumentService candidateDocumentService;

    @PostMapping("/upload")
    public ResponseEntity<CandidateDocumentDto> uploadDocument(
            @PathVariable long candidateId,
            @RequestParam String documentType,
            @RequestParam MultipartFile file){

       candidateDocumentService.uploadDocument(candidateId, documentType, file);
       return ResponseEntity.ok(new CandidateDocumentDto(
               candidateId,
               file.getOriginalFilename(),
               documentType,
               false
       ));
    }

    @PutMapping("/{documentId}/verify")
    public ResponseEntity<?> verifyDocument(
            @PathVariable Long candidateId,
            @PathVariable Long documentId) {
        candidateDocumentService.verifyDocument(candidateId, documentId);
        return ResponseEntity.ok(new MessageResponse("Document verified successfully!"));
    }
}
