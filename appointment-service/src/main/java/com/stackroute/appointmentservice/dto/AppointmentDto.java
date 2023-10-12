package com.stackroute.appointmentservice.dto;


import lombok.*;

import java.time.LocalTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppointmentDto {
    private long appointmentId;
    private long serviceId;
    private String customerEmailId;
    private String bookSlotDate;
    private LocalTime bookSlotStartTime;
    private LocalTime bookSlotEndTime;
    private String comment;
    private String employeeEmailId;

}