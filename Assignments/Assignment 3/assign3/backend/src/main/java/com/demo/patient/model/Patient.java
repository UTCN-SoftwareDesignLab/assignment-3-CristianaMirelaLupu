package com.demo.patient.model;

import com.demo.consultation.model.Consultation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 512, nullable = false)
    private String name;

    @Column(length = 512)
    private String card;

    @Column(length = 512)
    private String SSN;

    @Column
    private Date birthDate;

    @Column(length = 512)
    private String address;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE)
    private List<Consultation> consultations;
}
