package com.demo.patient;

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
public class PatientService {

    @Autowired
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    Patient findById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found: " + id));
    }
    public List<PatientDTO> findAll() {
        return patientRepository.findAll().stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }

    public PatientDTO create(PatientDTO patient) {

        return patientMapper.toDto(patientRepository.save(patientMapper.toPatient(patient)));
    }

    public PatientDTO edit(PatientDTO patient) {

        Patient actPatient = findById(patient.getId());

        actPatient.setName(patient.getName());
        actPatient.setIdNo(patient.getIdNo());
        actPatient.setSSN(patient.getSSN());
        actPatient.setBirthDate(patient.getBirthDate());
        actPatient.setAddress(patient.getAddress());

        return patientMapper.toDto(
                patientRepository.save(actPatient)
        );
    }

    public void deleteAll (){
        patientRepository.deleteAll();
    }

    public void deleteById (Long id) {
        patientRepository.deleteById(id);
    }

}
