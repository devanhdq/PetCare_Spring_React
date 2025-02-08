package com.petshop.controller;

import com.petshop.exception.ResourceNotFoundException;
import com.petshop.model.Photo;
import com.petshop.payload.response.ApiResponse;
import com.petshop.service.photo.PhotoService;
import com.petshop.utils.FeedBackMessage;
import com.petshop.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping(UrlMapping.PHOTO)
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @PostMapping(UrlMapping.UPLOAD_PHOTO)
    public ResponseEntity<ApiResponse> uploadPhoto(
            @RequestParam("file") MultipartFile file,
            @RequestParam Long userId) throws SQLException, IOException {
        try {
            Photo thePhoto = photoService.savePhoto(file, userId);
            return ResponseEntity.ok().body(new ApiResponse("Upload photo", FeedBackMessage.SUCCESS, thePhoto));
        } catch (IOException | SQLException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Upload photo", e.getMessage(), null));
        }
    }

    @PutMapping(UrlMapping.UPDATE_PHOTO)
    public ResponseEntity<ApiResponse> updatePhoto(@PathVariable Long photoId, @RequestBody byte[] photoBytes) throws SQLException {
        try {
            Photo photo = photoService.updatePhoto(photoId, photoBytes);
            return ResponseEntity.ok().body(new ApiResponse("Upload photo!", FeedBackMessage.SUCCESS, photo));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Upload photo!", e.getMessage(), null));
        } catch (SQLException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Upload photo!", e.getMessage(), null));
        }
    }

    @GetMapping(UrlMapping.GET_PHOTO)
    public ResponseEntity<ApiResponse> getPhotoById(@PathVariable Long photoId) {
        try {
            Photo photo = photoService.getPhotoById(photoId);
            return ResponseEntity.ok(new ApiResponse("Get photo by id", FeedBackMessage.SUCCESS, photo));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Get photo by id!", e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Get photo by id!", e.getMessage(), null));
        }
    }

    @DeleteMapping(UrlMapping.DELETE_PHOTO)
    public ResponseEntity<ApiResponse> deletePhotoById(@PathVariable Long photoId) {
        try {
            photoService.deletePhoto(photoId);
            return ResponseEntity.ok(new ApiResponse("Delete photo by id", FeedBackMessage.SUCCESS, null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Delete photo by id!", e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Delete photo by id!", e.getMessage(), null));
        }
    }
}
