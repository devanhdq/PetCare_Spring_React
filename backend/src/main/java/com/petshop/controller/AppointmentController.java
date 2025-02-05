package com.petshop.controller;

import com.petshop.model.Appointment;
import com.petshop.payload.response.ApiResponse;
import com.petshop.service.appointment.AppointmentService;
import com.petshop.utils.FeedBackMessage;
import com.petshop.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping(UrlMapping.APPOINTMENTS)
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping(UrlMapping.BOOK_APPOINTMENTS)
    public ResponseEntity<ApiResponse> bookAppointment(@RequestBody Appointment request) {
        return null;
    }

    @GetMapping(UrlMapping.GET_ALL_APPOINTMENTS)
    public ResponseEntity<ApiResponse> getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentService.getAllAppointments();
            return ResponseEntity.status(FOUND).body(new ApiResponse("Get all appointments!", FeedBackMessage.SUCCESS, appointments));
        } catch (Exception error) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Get all appointments!", FeedBackMessage.FAIL, null));
        }
    }

    @GetMapping(UrlMapping.GET_APPOINTMENTS_BY_ID)
    public ResponseEntity<ApiResponse> getAppointmentById(@PathVariable Long appointmentId) {
        return null;
    }

    @GetMapping(UrlMapping.GET_APPOINTMENTS_BY_APPOINTMENT_NO)
    public ResponseEntity<ApiResponse> getAppointmentByAppointmentNo(@PathVariable String appointmentNo) {
        return null;
    }

}
