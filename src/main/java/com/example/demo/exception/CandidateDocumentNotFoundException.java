package com.example.demo.exception;

public class CandidateDocumentNotFoundException extends RuntimeException {
    public CandidateDocumentNotFoundException(long candidateId, long documentId) {

        super("Candidate with ID " + candidateId + " does not have a document with ID " + documentId + " ");
    }
}
