package com.demo;

import com.demo.consultation.ConsultationRepository;
import com.demo.consultation.ConsultationService;
import com.demo.patient.PatientService;
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
                    .email("cristiana.wlf@gmail.com")
                    .username("cristiana_wlf")
                    .password("Aleluia123!")
                    .roles(Set.of("ADMIN"))
                    .build());

            authService.register(SignupRequest.builder()
                    .email("cristiana.wlf@yahoo.com")
                    .username("cristiana_wolf")
                    .password("Aleluia123!")
                    .roles(Set.of("SECRETARY"))
                    .build());

            authService.register(SignupRequest.builder()
                    .email("cristiana.wlf1@yahoo.com")
                    .username("cristiana_wolf1")
                    .password("Aleluia123!")
                    .roles(Set.of("DOCTOR"))
                    .build());
        }
    }
}
