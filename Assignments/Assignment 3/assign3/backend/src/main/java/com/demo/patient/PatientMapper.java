package com.demo.patient;

import com.demo.patient.model.Patient;
import com.demo.patient.model.dto.PatientDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDTO toDto(Patient patient);

    Patient toPatient(PatientDTO patient);

}
