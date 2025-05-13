package com.example.demo.service;

import com.example.demo.dto.CandidateBankInfoDto;
import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.CandidateEducationDto;
import com.example.demo.dto.CandidatePersonalInfoDto;
import com.example.demo.entity.Candidate;
import com.example.demo.entity.CandidateBankInfo;
import com.example.demo.entity.CandidateEducation;
import com.example.demo.entity.CandidatePersonalInfo;
import com.example.demo.enums.OnboardingStatus;
import com.example.demo.enums.Status;
import com.example.demo.exception.CandidateNotFoundException;
import com.example.demo.repository.CandidateBankInfoRepository;
import com.example.demo.repository.CandidateEducationRepository;
import com.example.demo.repository.CandidatePersonalInfoRepository;
import com.example.demo.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {
    CandidateRepository candidateRepository;
    CandidatePersonalInfoRepository candidatePersonalInfoRepository;
    CandidateBankInfoRepository candidateBankInfoRepository;
    CandidateEducationRepository candidateEducationRepository;

    @Autowired
    CandidateService(CandidateRepository candidateRepository, CandidatePersonalInfoRepository candidatePersonalInfoRepository,
                     CandidateBankInfoRepository candidateBankInfoRepository, CandidateEducationRepository candidateEducationRepository) {
        this.candidateRepository = candidateRepository;
        this.candidatePersonalInfoRepository = candidatePersonalInfoRepository;
        this.candidateBankInfoRepository = candidateBankInfoRepository;
        this.candidateEducationRepository = candidateEducationRepository;

    }

    public void addNewCandidate(CandidateDto candidateDto) {
        Candidate candidate = new Candidate();
        candidate.setFirstName(candidateDto.getFirstName());
        candidate.setLastName(candidateDto.getLastName());
        candidate.setPhoneNumber(candidateDto.getPhoneNumber());
        candidate.setEmail(candidateDto.getEmail());
        candidateRepository.save(candidate);
    }

    public Candidate getCandidateById(Long id) {
        Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new CandidateNotFoundException(id));
        return candidate;
    }

    public int getCandidateCount() {
        int count = 0;
        for (Candidate candidate : candidateRepository.findAll()) {
            count++;
        }
        return count;
    }

    public Status getCandidateStatus(Long id) {
        Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new CandidateNotFoundException(id));
        return candidate.getStatus();
    }

    public OnboardingStatus getCandidateOnboardStatus(Long id) {
        Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new CandidateNotFoundException(id));
        return candidate.getOnboardingStatus();
    }

    public void addPersonalInfo(CandidatePersonalInfoDto candidatePersonalInfoDto, Long candidateId) {
        CandidatePersonalInfo candidatePersonalInfo = new CandidatePersonalInfo();
        candidatePersonalInfo.setDob(candidatePersonalInfoDto.getDob());
        candidatePersonalInfo.setAddress( candidatePersonalInfoDto.getAddress());
        candidatePersonalInfo.setGender(candidatePersonalInfoDto.getGender());
        candidatePersonalInfo.setNationality(candidatePersonalInfoDto.getNationality());
        candidatePersonalInfo.setCandidate(candidateRepository.findById(candidateId).orElseThrow(() -> new CandidateNotFoundException(candidateId)));
        candidatePersonalInfoRepository.save(candidatePersonalInfo);
    }

    public void addCandidateEducation(CandidateEducationDto candidateEducationDto, Long candidateId) {
        CandidateEducation candidateEducation = new CandidateEducation();
        candidateEducation.setDegree(candidateEducationDto.getDegree());
        candidateEducation.setInstitution(candidateEducationDto.getInstitution());
        candidateEducation.setYearOfGraduation( candidateEducationDto.getYearOfGraduation());
        candidateEducation.setCandidate(candidateRepository.findById(candidateId).orElseThrow(() -> new CandidateNotFoundException(candidateId)));
        candidateEducationRepository.save(candidateEducation);
    }

    public void updateCandidateStatus(Long id, Status status){
        Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new CandidateNotFoundException(id));
        candidate.setStatus(status);
        candidateRepository.save(candidate);
    }

    public void updateCandidateOnboardStatus(Long id, OnboardingStatus status){
        Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new CandidateNotFoundException(id));
        candidate.setOnboardingStatus(status);
    }

    public CandidatePersonalInfo getCandidatePersonalInfo(Long candidateId){
        try{
            return candidatePersonalInfoRepository.findByCandidateId(candidateId);
        }catch (CandidateNotFoundException e){
            throw new CandidateNotFoundException(candidateId);
        }
    }

    public CandidateEducation getCandidateEducation(Long candidateId){
        try{
            return candidateEducationRepository.findByCandidateId(candidateId);
        }catch (CandidateNotFoundException e){
            throw new CandidateNotFoundException(candidateId);
        }
    }

    public void addBankInfo(CandidateBankInfoDto candidateBankInfoDto, long candidateId) {
        CandidateBankInfo candidateBankInfo = new CandidateBankInfo();
        candidateBankInfo.setAccountNumber(candidateBankInfoDto.getAccountNumber());
        candidateBankInfo.setBankName(candidateBankInfo.getBankName());
        candidateBankInfo.setIfscCode(candidateBankInfo.getIfscCode());
        candidateBankInfo.setCandidate(candidateRepository.findById(candidateId).orElseThrow(() -> new CandidateNotFoundException(candidateId)));
        candidateBankInfoRepository.save(candidateBankInfo);
    }
}
