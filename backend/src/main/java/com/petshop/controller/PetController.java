package com.petshop.controller;

import com.petshop.exception.ResourceNotFoundException;
import com.petshop.model.Pet;
import com.petshop.payload.response.ApiResponse;
import com.petshop.service.pet.PetService;
import com.petshop.utils.FeedBackMessage;
import com.petshop.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping(UrlMapping.PETS)
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

    @GetMapping(UrlMapping.GET_ALL_PETS)
    public ResponseEntity<ApiResponse> getAllPets() {
        try {
            List<Pet> pets = petService.getAllPets();
            return ResponseEntity.ok(new ApiResponse("Get all pets!", FeedBackMessage.SUCCESS, pets));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Get all pets!", FeedBackMessage.FAIL, null));
        }
    }

    @GetMapping(UrlMapping.GET_PET_BY_ID)
    public ResponseEntity<ApiResponse> getPetById(@PathVariable Long petId) {
        try {
            Pet pet = petService.getPetById(petId);
            return ResponseEntity.ok(new ApiResponse("Get pet by id!", FeedBackMessage.SUCCESS, pet));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Get pet by id!", FeedBackMessage.FAIL, null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Get pet by id!", FeedBackMessage.FAIL, null));
        }
    }

    @PostMapping(UrlMapping.ADD_PET)
    public ResponseEntity<ApiResponse> addPetForAppointment(@RequestBody List<Pet> pets) {
        try {
            List<Pet> newPets = petService.savePetsForAppointment(pets);
            return ResponseEntity.ok(new ApiResponse("Add pet for appointment!", FeedBackMessage.SUCCESS, newPets));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Add pet for appointment!", FeedBackMessage.FAIL, null));
        }
    }

    @PutMapping(UrlMapping.UPDATE_PET_BY_ID)
    public ResponseEntity<ApiResponse> updatePetById(@PathVariable Long petId, @RequestBody Pet pet) {
        try {
            Pet updatedPet = petService.updatePetById(petId, pet);
            return ResponseEntity.ok(new ApiResponse("Update pet by id!", FeedBackMessage.SUCCESS, updatedPet));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Update pet by id!", FeedBackMessage.FAIL, null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Update pet by id!", FeedBackMessage.FAIL, null));
        }
    }

    @DeleteMapping(UrlMapping.DELETE_PET_BY_ID)
    public ResponseEntity<ApiResponse> deletePetById(@PathVariable Long petId) {
        try {
            petService.deletePetById(petId);
            return ResponseEntity.ok(new ApiResponse("Delete pet by id!", FeedBackMessage.SUCCESS, null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Delete pet by id!", FeedBackMessage.FAIL, null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Delete pet by id!", FeedBackMessage.FAIL, null));
        }
    }
}
