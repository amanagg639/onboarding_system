package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateEducation {
    @Id @GeneratedValue
    private long id;
    private String degree;
    private String institution;
    private String yearOfGraduation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    @ToString.Exclude
    private Candidate candidate;
}
