package com.petshop.service.photo;

import com.petshop.exception.ResourceNotFoundException;
import com.petshop.model.Photo;
import com.petshop.model.User;
import com.petshop.repository.PhotoRepository;
import com.petshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class PhotoService implements IPhotoService {

    private final PhotoRepository photoRepository;

    private final UserRepository userRepository;


    @Override
    public Photo savePhoto(MultipartFile file, Long userId) throws IOException, SQLException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Oop! User not found"));
        Photo photo = new Photo();
        if (file != null && !file.isEmpty()) {
            byte[] photoBytes = file.getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);
            photo.setImage(photoBlob);
            photo.setFileType(file.getContentType());
            photo.setFileName(file.getOriginalFilename());
        }
        Photo savedPhoto = photoRepository.save(photo);
        user.setPhoto(savedPhoto);
        userRepository.save(user);
        return savedPhoto;
    }

    @Override
    public Photo getPhotoById(Long id) {
        return photoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Oop! Photo not found!"));
    }

    @Transactional
    @Override
    public void deletePhoto(Long id, Long userId) {
        userRepository.findById(userId).ifPresentOrElse(User::removeUserPhoto, () -> {
            throw new ResourceNotFoundException("Oop! User not found!");
        });
        photoRepository.findById(id)
                .ifPresentOrElse(photoRepository::delete, () -> {
                    throw new ResourceNotFoundException("Oop! Photo not found!");
                });

    }

    @Override
    public Photo updatePhoto(Long id, MultipartFile file) throws SQLException, IOException {
        Photo photo = getPhotoById(id);
        if (photo != null) {
            byte[] photoBytes = file.getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);
            photo.setImage(photoBlob);
            photo.setFileType(file.getContentType());
            photo.setFileName(file.getOriginalFilename());
            return photoRepository.save(photo);
        }
        throw new ResourceNotFoundException("Oop! Photo not found!");
    }
    @Override
    public byte[] getImageData(Long id) throws SQLException {
        Photo photo = getPhotoById(id);
        if (photo != null) {
            Blob photoBlob = photo.getImage();
            int blobLength = (int) photoBlob.length();
            return new byte[blobLength];
        }
        return null;
    }

}
