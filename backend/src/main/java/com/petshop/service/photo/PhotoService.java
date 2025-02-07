package com.petshop.service.photo;

import com.petshop.exception.ResourceNotFoundException;
import com.petshop.model.Photo;
import com.petshop.model.User;
import com.petshop.repository.PhotoRepository;
import com.petshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class PhotoService implements IPhotoService {

    private final PhotoRepository photoRepository;

    private final UserRepository ueRepository;

    @Override
    public Photo savePhoto(MultipartFile file, Long userId) throws IOException, SQLException {
        User user = ueRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("Oop! User not found")
        );
        Photo photo = new Photo();
        if (file != null && !file.isEmpty()) {
            byte[] photoBytes = file.getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);
            photo.setImage(photoBlob);
            photo.setFileType(file.getContentType());
        }
        Photo savedPhoto = photoRepository.save(photo);

        user.setPhoto(savedPhoto);
        ueRepository.save(user);
        return savedPhoto;
    }

    @Override
    public Photo getPhotoById(Long photoId) {
        return photoRepository.findById(photoId).orElseThrow(
                () -> new ResourceNotFoundException("Oop! User not found")
        );
    }

    @Override
    public void deletePhoto(Long photoId) {
        photoRepository.findById(photoId).ifPresentOrElse(photoRepository::delete, () -> {
            throw new ResourceNotFoundException("Oop! User not found");
        });
    }

    @Override
    public Photo updatePhoto(Long id, byte[] file) throws SQLException {
        Photo photo = getPhotoById(id);
        Blob photoBlob = new SerialBlob(file);
        photo.setImage(photoBlob);
        return photoRepository.save(photo);
    }

    @Override
    public byte[] getPhotoImage(Long photoId) throws SQLException {
        Photo photo = getPhotoById(photoId);
        Blob photoBlob = photo.getImage();
        int blobLength = (int) photoBlob.length();
        return new byte[blobLength];
    }
}
