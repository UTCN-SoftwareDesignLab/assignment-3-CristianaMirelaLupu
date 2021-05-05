package com.demo.consultation;

import com.demo.consultation.model.Consultation;
import com.demo.consultation.model.dto.ConsultationDTO;
import com.demo.patient.model.Patient;
import com.demo.patient.model.dto.PatientDTO;
import org.mapstruct.Mapper;


    @Mapper(componentModel = "spring")
    public interface ConsultationMapper {

        ConsultationDTO toDto(Consultation consultation);
        Consultation toConsultation (ConsultationDTO consultation);

}
