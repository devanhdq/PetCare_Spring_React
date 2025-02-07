package com.petshop.service.photo;

import com.petshop.model.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

public interface IPhotoService {

    Photo savePhoto(MultipartFile file, Long userId) throws IOException, SQLException;

    Photo getPhotoById(Long photoId);

    void deletePhoto(Long photoId);

    Photo updatePhoto(Long id, byte[] file) throws SQLException;

    byte[] getPhotoImage(Long photoId) throws SQLException;


}
