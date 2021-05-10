package com.demo.consultation.model.dto;

import com.demo.patient.model.Patient;
import com.demo.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ConsultationDTO {
    private Long id;
    private String description;
    private Patient patient;
    private User doctor;
    private Date consultationStartDate;
    private Date consultationEndDate;
}
