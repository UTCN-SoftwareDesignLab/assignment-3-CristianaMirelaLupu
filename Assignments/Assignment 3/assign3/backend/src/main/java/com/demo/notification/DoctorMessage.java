package com.demo.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class DoctorMessage {
    private String patient;
    private String doctor;
    private String dateAndTime;
}
