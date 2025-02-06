package com.petshop.payload.request.appointment;

import com.petshop.model.Appointment;
import com.petshop.model.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookAppointmentRequest {

    private Appointment appointment;

    private List<Pet> pets;
}
