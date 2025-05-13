package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.Candidate;
import com.example.demo.entity.CandidateEducation;
import com.example.demo.entity.CandidatePersonalInfo;
import com.example.demo.enums.OnboardingStatus;
import com.example.demo.enums.Status;
import com.example.demo.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidate")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @PostMapping
    public ResponseEntity<?> saveNewCandidate(@RequestBody @Validated CandidateDto candidateDto) {
        candidateService.addNewCandidate(candidateDto);
        return ResponseEntity.ok(new MessageResponse("Candidate added successfully!"));
    }

    @PostMapping("/{candidateId}/personal-info")
    public ResponseEntity<?>  addPersonalInfo(@RequestBody @Validated CandidatePersonalInfoDto candidatePersonalInfoDto, @PathVariable long candidateId) {
        candidateService.addPersonalInfo(candidatePersonalInfoDto, candidateId);
        return ResponseEntity.ok(new MessageResponse("Candidate personal info added successfully!"));
    }

    @PostMapping("/{CandidateId}/education-info")
    public ResponseEntity<?>  addCandidateEducation(@RequestBody @Validated CandidateEducationDto candidateEducationDto, @PathVariable long candidateId) {
        candidateService.addCandidateEducation(candidateEducationDto, candidateId);
        return ResponseEntity.ok(new MessageResponse("Candidate education added successfully!"));
    }

    @PostMapping("/{candidateId}/bank-info")
    public ResponseEntity<?>  addBankInfo(@RequestBody @Validated CandidateBankInfoDto candidateBankInfoDto, @PathVariable long candidateId) {
        candidateService.addBankInfo(candidateBankInfoDto, candidateId);
        return ResponseEntity.ok(new MessageResponse("Candidate bank info added successfully!"));
    }
    

    @GetMapping
    public String getAll(){
        return "All Candidates";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCandidatesById(@PathVariable long id){
        Candidate candidate = candidateService.getCandidateById(id);
        return ResponseEntity.ok(new CandidateDto(
                candidate.getFirstName(),
                candidate.getLastName(),
                candidate.getPhoneNumber(),
                candidate.getEmail()
        ));
    }

    @GetMapping("/count")
    public ResponseEntity<?> getCandidatesCount(){
        int cnt = candidateService.getCandidateCount();
        return ResponseEntity.ok(new MessageResponse("Total Candidates: " + cnt));
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<?> getCandidatesStatus(@PathVariable long id){
        Status status = candidateService.getCandidateStatus(id);
        return ResponseEntity.ok(new MessageResponse("Status: " + status));
    }

    @GetMapping("/{id}/onboardStatus")
    public ResponseEntity<?> getCandidatesOnboardStatus(@PathVariable long id){
        OnboardingStatus status = candidateService.getCandidateOnboardStatus(id);
        return ResponseEntity.ok(new MessageResponse("Status: " + status));
    }

    @GetMapping("/{candidateId}/personal-info")
    public  ResponseEntity<?> getCandidatePersonalInfo(@PathVariable long candidateId){
        CandidatePersonalInfo candidatePersonalInfo = candidateService.getCandidatePersonalInfo(candidateId);
        return ResponseEntity.ok(new CandidatePersonalInfoDto(
                candidatePersonalInfo.getDob(),
                candidatePersonalInfo.getAddress(),
                candidatePersonalInfo.getGender(),
                candidatePersonalInfo.getNationality()
        ));
    }

    @GetMapping("/{candidateId}/education-info")
    public  ResponseEntity<?> getCandidateEducationInfo(@PathVariable long candidateId){
        CandidateEducation candidateEducation = candidateService.getCandidateEducation(candidateId);
        return ResponseEntity.ok(new CandidateEducationDto(
                candidateEducation.getDegree(),
                candidateEducation.getInstitution(),
                candidateEducation.getYearOfGraduation()
        ));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateCandidateStatus(@PathVariable long id, @RequestBody Status status){
        candidateService.updateCandidateStatus(id, status);
        return ResponseEntity.ok(new MessageResponse("Candidate status updated successfully!"));
    }

    @PutMapping("/{id}/onboardStatus")
    public ResponseEntity<?> updateCandidateOnboardStatus(@PathVariable long id, @RequestBody OnboardingStatus status){
        candidateService.updateCandidateOnboardStatus(id, status);
        return ResponseEntity.ok(new MessageResponse("Candidate onboarding status updated successfully!"));
    }
}
