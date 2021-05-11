package com.demo.consultation.model.dto;

import com.demo.patient.model.Patient;
import com.demo.patient.model.dto.PatientDTO;
import com.demo.user.dto.UserListDTO;
import com.demo.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ConsultationDTO {
    private Long id;
    private String description;
    private String patient;
    private String doctor;
    private LocalDateTime date;
}
