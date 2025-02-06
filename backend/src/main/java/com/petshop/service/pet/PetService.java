package com.petshop.service.pet;

import com.petshop.exception.ResourceNotFoundException;
import com.petshop.model.Pet;
import com.petshop.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService implements IPetService {
    private final PetRepository petRepository;


    @Override
    public List<Pet> savePetsForAppointment(List<Pet> pets) {
        return petRepository.saveAll(pets);
    }

    @Override
    public Pet updatePetById(Long petId, Pet pet) {
        Pet existingPet = getPetById(petId);
        existingPet.setName(pet.getName());
        existingPet.setType(pet.getType());
        existingPet.setColor(pet.getColor());
        existingPet.setBreed(pet.getBreed());
        existingPet.setAge(pet.getAge());
        return petRepository.save(existingPet);
    }

    @Override
    public void deletePetById(Long petId) {
        petRepository.findById(petId)
                .ifPresentOrElse(petRepository::delete, () -> {
                    throw new ResourceNotFoundException("Pet not found");
                });
    }

    @Override
    public Pet getPetById(Long petId) {
        return petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found"));
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }
}
