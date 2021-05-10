package com.demo.patient.model.dto;

import com.demo.consultation.model.Consultation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private Long id;
    private String name;
    private String card;
    private String SSN;
    private Date birthDate;
    private String address;
    private List<Consultation> consultations;
}
