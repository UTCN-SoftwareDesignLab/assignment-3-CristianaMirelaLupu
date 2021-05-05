package com.demo.consultation;

import com.demo.consultation.model.Consultation;
import com.demo.consultation.model.dto.ConsultationDTO;
import com.demo.patient.PatientMapper;
import com.demo.patient.PatientRepository;
import com.demo.patient.model.Patient;
import com.demo.patient.model.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultationService {

    @Autowired
    private final ConsultationRepository consultationRepository;
    private final ConsultationMapper consultationMapper;

    Consultation findById(Long id) {
        return consultationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consultation not found: " + id));
    }
    public List<ConsultationDTO> findAll() {
        return consultationRepository.findAll().stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }

    public ConsultationDTO create(ConsultationDTO consultation) {

        return consultationMapper.toDto(consultationRepository.save(consultationMapper.toConsultation(consultation)));
    }

    public ConsultationDTO edit(ConsultationDTO consultation) {

        Consultation actConsultation = findById(consultation.getId());

        actConsultation.setName(consultation.getName());
        actConsultation.setPatient(consultation.getPatient());
        actConsultation.setDoctor(consultation.getDoctor());

        return consultationMapper.toDto(
                consultationRepository.save(actConsultation)
        );
    }

    public void deleteAll (){
        consultationRepository.deleteAll();
    }

    public void deleteById (Long id) {
        consultationRepository.deleteById(id);
    }


}
