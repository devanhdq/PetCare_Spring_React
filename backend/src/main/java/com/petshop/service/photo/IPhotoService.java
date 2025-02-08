package com.petshop.service.photo;

import com.petshop.model.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

public interface IPhotoService {
    Photo savePhoto(MultipartFile file, Long userId) throws IOException, SQLException;

    Photo getPhotoById(Long id);

    void deletePhoto(Long id, Long userId) throws SQLException;

    Photo updatePhoto(Long id, MultipartFile file) throws SQLException, IOException;

    byte[] getImageData(Long id) throws SQLException;
}
