package com.petshop.payload.request.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentUpdateRequest {

    private String appointmentDate;

    private String appointmentTime;

    private String reason;

}
