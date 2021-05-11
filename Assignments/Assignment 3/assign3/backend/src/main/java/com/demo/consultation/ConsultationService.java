package com.demo.consultation;

import com.demo.consultation.model.Consultation;
import com.demo.consultation.model.dto.ConsultationDTO;
import com.demo.patient.PatientMapper;
import com.demo.patient.PatientRepository;
import com.demo.patient.model.Patient;
import com.demo.user.UserRepository;
import com.demo.user.UserService;
import com.demo.user.mapper.UserMapper;
import com.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultationService {

    @Autowired
    private final ConsultationRepository consultationRepository;
    private final ConsultationMapper consultationMapper;
    private final PatientMapper patientMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PatientRepository patientRepository;
    private final UserService userService;

    Consultation findById(Long id) {
        return consultationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consultation not found: " + id));
    }

    public List<ConsultationDTO> findAll() {
        return consultationRepository.findAll().stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }

    public ConsultationDTO create(ConsultationDTO consultation) throws Exception {

        User doctor = userService.findDoctorByName(consultation.getDoctor());

        Patient patient = patientRepository.findPatientByName(consultation.getPatient())
                .orElseThrow(()->new EntityNotFoundException("Patient not found"));

        Consultation actConsult = new Consultation().builder()
                .id(consultation.getId())
                .description(consultation.getDescription())
                .patient(patient)
                .doctor(doctor)
                .dateAndTime(consultation.getDate())
                .build();

        List<ConsultationDTO> all = findAll();

        List<ConsultationDTO> areThereConflicts = all.stream().
                filter(dto -> dto.getDate().equals(consultation.getDate())
                        && dto.getDoctor().equals(consultation.getDoctor())).collect(Collectors.toList());

        if(areThereConflicts.isEmpty()) {
            Consultation consultations = consultationMapper.toConsultation(consultation);
            consultations.setDateAndTime(consultation.getDate());
            return consultationMapper.toDto(consultationRepository.save(actConsult));
        }

        else {
            throw new Exception("The selected time is unavailable, conflict arises between this and another consultation.");
        }
    }

    public ConsultationDTO edit(ConsultationDTO consultation) {

        Consultation actConsultation = findById(consultation.getId());

        actConsultation.setDescription(consultation.getDescription());
//        actConsultation.setPatient(consultation.getPatient());
//        actConsultation.setDoctor(consultation.getDoctor());
        actConsultation.setDateAndTime(consultation.getDate());

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
