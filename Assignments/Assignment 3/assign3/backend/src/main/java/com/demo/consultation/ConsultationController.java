package com.demo.consultation;

import com.demo.UrlMapping;
import com.demo.consultation.model.dto.ConsultationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlMapping.CONSULTATION)
@RequiredArgsConstructor

public class ConsultationController {

    private final ConsultationService consultationService;

    @GetMapping
    public List<ConsultationDTO> allConsultations() {
        return consultationService.findAll();
    }

    @PostMapping
    public ConsultationDTO create(@RequestBody ConsultationDTO consultation) throws Exception {
        return consultationService.create(consultation);
    }

    @PutMapping
    public ConsultationDTO edit(@RequestBody ConsultationDTO consultation) {
        return consultationService.edit(consultation);
    }

    @DeleteMapping
    public void deleteAll() { consultationService.deleteAll(); }

    @DeleteMapping(UrlMapping.ID)
    public void deleteById(@PathVariable String id) {
        consultationService.deleteById(Long.parseLong(id));}
}
