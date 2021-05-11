package com.demo;

import com.demo.consultation.ConsultationRepository;
import com.demo.consultation.ConsultationService;
import com.demo.consultation.model.dto.ConsultationDTO;
import com.demo.patient.PatientService;
import com.demo.patient.model.dto.PatientDTO;
import com.demo.security.AuthService;
import com.demo.patient.PatientRepository;
import com.demo.security.dto.SignupRequest;
import com.demo.user.RoleRepository;
import com.demo.user.UserRepository;
import com.demo.user.model.ERole;
import com.demo.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final PatientRepository patientRepository;

    private final PatientService patientService;

    private final ConsultationRepository consultationRepository;

    private final ConsultationService consultationService;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            patientRepository.deleteAll();
            consultationRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();

            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("admin.admin@gmail.com")
                    .username("admin")
                    .password("Aleluia123!")
                    .roles(Set.of("ADMIN"))
                    .build());

            authService.register(SignupRequest.builder()
                    .email("secretary.secretary@gmail.com")
                    .username("secretary")
                    .password("Aleluia123!")
                    .roles(Set.of("SECRETARY"))
                    .build());

            authService.register(SignupRequest.builder()
                    .email("doctor.doctor@gmail.com")
                    .username("doctor")
                    .password("Aleluia123!")
                    .roles(Set.of("DOCTOR"))
                    .build());

            patientService.create(PatientDTO.builder()
                    .name("patient1")
                    .card("1234")
                    .SSN("123445678910")
                    .birthDate(Date.valueOf(LocalDate.now()))
                    .address("Cluj-Napoca")
                    .build());

            patientService.create(PatientDTO.builder()
                    .name("patient2")
                    .card("9874")
                    .SSN("123445678911")
                    .birthDate(Date.valueOf(LocalDate.now()))
                    .address("Cluj-Napoca")
                    .build());
        }
    }
}
