package com.demo.patient;

import com.demo.UrlMapping;
import com.demo.patient.model.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlMapping.PATIENT)
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public List<PatientDTO> allPatients() {
        return patientService.findAll();
    }

    @PostMapping
    public PatientDTO create(@RequestBody PatientDTO patient) {
        return patientService.create(patient);
    }

    @PutMapping
    public PatientDTO edit(@RequestBody PatientDTO patient) {
        return patientService.edit(patient);
    }

    @DeleteMapping
    public void deleteAll() { patientService.deleteAll(); }

    @DeleteMapping(UrlMapping.ID)
    public void deleteById(@PathVariable String id) {
        patientService.deleteById(Long.parseLong(id));}
}
