package com.petshop.controller;

import com.petshop.exception.ResourceNotFoundException;
import com.petshop.model.Appointment;
import com.petshop.payload.request.appointment.AppointmentUpdateRequest;
import com.petshop.payload.response.ApiResponse;
import com.petshop.service.appointment.AppointmentService;
import com.petshop.utils.FeedBackMessage;
import com.petshop.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(UrlMapping.APPOINTMENTS)
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping(UrlMapping.BOOK_APPOINTMENTS)
    public ResponseEntity<ApiResponse> bookAppointment(
            @RequestBody Appointment request,
            @RequestParam Long senderId,
            @RequestParam Long recipientId) {
        try {
            Appointment theAppointment = appointmentService.createAppointment(request, senderId, recipientId);
            return ResponseEntity.status(CREATED).body(new ApiResponse("Book appointment!", FeedBackMessage.SUCCESS, theAppointment));
        } catch (ResourceNotFoundException error) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Book appointment!", error.getMessage(), null));
        } catch (Exception error) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Book appointment!", error.getMessage(), null));
        }

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

    @GetMapping(UrlMapping.GET_APPOINTMENT_BY_ID)
    public ResponseEntity<ApiResponse> getAppointmentById(@PathVariable Long appointmentId) {
        try {
            Appointment appointment = appointmentService.getAppointmentById(appointmentId);
            return ResponseEntity.status(FOUND).body(new ApiResponse("Get appointment by id", FeedBackMessage.SUCCESS, appointment));
        } catch (ResourceNotFoundException error) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Get appointment by id", error.getMessage(), null));
        } catch (Exception error) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Get appointment by id", error.getMessage(), null));
        }
    }

    @GetMapping(UrlMapping.GET_APPOINTMENT_BY_APPOINTMENT_NO)
    public ResponseEntity<ApiResponse> getAppointmentByAppointmentNo(@PathVariable String appointmentNo) {
        try {
            Appointment appointment = appointmentService.getAppointmentByAppointmentNo(appointmentNo);
            return ResponseEntity.status(FOUND).body(new ApiResponse("Get appointment by AppointmentNo", FeedBackMessage.SUCCESS, appointment));
        } catch (ResourceNotFoundException error) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Get appointment by AppointmentNo", error.getMessage(), null));
        } catch (Exception error) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Get appointment by AppointmentNo", error.getMessage(), null));
        }
    }

    @PutMapping(UrlMapping.UPDATE_APPOINTMENT_BY_ID)
    public ResponseEntity<ApiResponse> updateAppointment(
            @PathVariable Long appointmentId,
            @RequestBody AppointmentUpdateRequest request) {
        try {
            Appointment updatedAppointment = appointmentService.updateAppointmentById(appointmentId, request);
            return ResponseEntity.ok(new ApiResponse("Update Appointment", FeedBackMessage.SUCCESS, updatedAppointment));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(BAD_REQUEST).body(new ApiResponse("Update Appointment", e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Update Appointment", e.getMessage(), null));
        }

    }

    @DeleteMapping(UrlMapping.DELETE_APPOINTMENT_BY_ID)
    public ResponseEntity<ApiResponse> deleteAppointmentById(@PathVariable Long appointmentId) {
        try {
            appointmentService.deleteAppointmentById(appointmentId);
            return ResponseEntity.ok().body(new ApiResponse("Delete appointment by id!", FeedBackMessage.SUCCESS, null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Delete appointment by id!", e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Delete appointment by id!", e.getMessage(), null));
        }
    }

}
