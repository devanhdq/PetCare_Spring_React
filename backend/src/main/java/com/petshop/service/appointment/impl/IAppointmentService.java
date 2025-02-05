package com.petshop.service.appointment.impl;

import com.petshop.model.Appointment;
import com.petshop.payload.request.appointment.AppointmentUpdateRequest;

import java.util.List;

public interface IAppointmentService {

    Appointment createAppointment(Appointment appointment, Long sender, Long recipient);

    List<Appointment> getAllAppointments();

    Appointment getAppointmentById(Long appointmentId);

    Appointment getAppointmentByAppointmentNo(String appointmentNo);

    Appointment updateAppointmentById(Long appointmentId, AppointmentUpdateRequest request);

    void deleteAppointmentById(Long appointmentId);

}
