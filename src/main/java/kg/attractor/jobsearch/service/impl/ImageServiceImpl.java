package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.ImageDao;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.errors.CanNotFindImageException;
import kg.attractor.jobsearch.errors.CanNotFindUserException;
import kg.attractor.jobsearch.service.ImageService;
import kg.attractor.jobsearch.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final FileUtil fileUtil;
    private final ImageDao imageDao;

    @Override
    public String uploadAvatar(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            log.error("Attempted to upload an empty or null file");
            throw new IllegalArgumentException("File must not be empty or null");
        }

        try {
            return ImageService.uploadImage(file);
        } catch (Exception e) {
            log.error("An error occurred while uploading avatar: {}", e.getMessage());
            throw new CanNotFindImageException("Unable to upload avatar");
        }
    }

    @Override
    public ResponseEntity<?> download(String name) {
        if (name == null || name.isEmpty()) {
            log.error("Attempted to download an image with an empty or null name");
            return ResponseEntity.badRequest().body("Image name must not be empty or null");
        }

        try {
            return ImageService.downloadImage(name, MediaType.IMAGE_JPEG);
        } catch (Exception e) {
            log.error("An error occurred while downloading image: {}", e.getMessage());
            throw new CanNotFindImageException("Unable to download image with name " + name);
        }
    }

    @Override
    public ResponseEntity<InputStreamResource> downloadImage(String name) {
        if (name == null || name.isEmpty()) {
            log.error("Attempted to download an image with an empty or null name");
            return ResponseEntity.badRequest().body(null); // Return bad request with null body
        }

        return fileUtil.getOutputFile(name, "/images");
    }

    @Override
    public String uploadImage(MultipartFile avatar, Authentication authentication) {
        if (avatar == null || avatar.isEmpty()) {
            log.error("Attempted to upload an empty or null file");
            throw new IllegalArgumentException("File must not be empty or null");
        }

        String contentType = avatar.getContentType();
        if (contentType == null || !contentType.matches("image/png|image/jpeg")) {
            throw new IllegalArgumentException("Unsupported image type (should be: \"png/jpeg\")");
        }

        String fileName;
        try {
            fileName = fileUtil.saveUploadedFile(avatar, "/images");
        } catch (Exception e) {
            log.error("An error occurred while saving the uploaded file: {}", e.getMessage());
            throw new CanNotFindImageException("Unable to save uploaded image");
        }

        UserDto user;
        try {
            user = imageDao.findUserByEmail(authentication.getName());
            if (user == null) {
                throw new CanNotFindUserException("User by email " + authentication.getName() + " not found");
            }
        } catch (Exception e) {
            log.error("An error occurred while finding the user by email: {}", e.getMessage());
            throw new CanNotFindUserException("Unable to find user");
        }

        try {
            user.setAvatar(fileName);
            imageDao.updateUser(user);
        } catch (Exception e) {
            log.error("An error occurred while updating the user with the new avatar: {}", e.getMessage());
            throw new CanNotFindUserException("Unable to update user with new avatar");
        }
        return contentType;
    }
}
