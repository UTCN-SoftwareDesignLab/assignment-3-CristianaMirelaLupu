package com.demo.consultation;

import com.demo.consultation.model.Consultation;
import com.demo.consultation.model.dto.ConsultationDTO;
import com.demo.patient.model.Patient;
import com.demo.patient.model.dto.PatientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
    public interface ConsultationMapper {

        @Mappings({
                @Mapping(target = "patient", expression = "java(consultation.getPatient().getName())"),
                @Mapping(target = "doctor", expression = "java(consultation.getDoctor().getUsername())")
        })
        ConsultationDTO toDto(Consultation consultation);

    @Mappings({
            @Mapping(target = "patient.name", source = "patient"),
            @Mapping(target = "doctor.username", source = "doctor")
    })
        Consultation toConsultation (ConsultationDTO consultationDTO);
}
