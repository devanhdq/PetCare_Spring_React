package com.petshop.service.pet;

import com.petshop.model.Pet;

import java.util.List;

public interface IPetService {

    List<Pet> savePetsForAppointment(List<Pet> pets);

    Pet updatePetById(Long petId, Pet pet);

    void deletePetById(Long petId);

    Pet getPetById(Long petId);

    List<Pet> getAllPets();
}
