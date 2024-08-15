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
        try {
            return ImageService.uploadImage(file);
        } catch (Exception e) {
            log.error("An error occurred while uploading avatar: {}", e.getMessage());
            throw new CanNotFindImageException("Unable to upload avatar");
        }
    }

    @Override
    public ResponseEntity<?> download(String name) {
        try {
            return ImageService.downloadImage(name, MediaType.IMAGE_JPEG);
        } catch (Exception e) {
            log.error("An error occurred while downloading image: {}", e.getMessage());
            throw new CanNotFindImageException("Unable to download image with name " + name);
        }
    }

    @Override
    public ResponseEntity<InputStreamResource> downloadImage(String name) {
        return fileUtil.getOutputFile(name, "/images");
    }

    @Override
    public void uploadImage(MultipartFile avatar, Authentication authentication) {
        if (avatar != null) {
            String contentType = avatar.getContentType();
            if (contentType == null || !contentType.matches("image/png|image/jpeg")) {
                throw new IllegalArgumentException("Unsupported image type (should be: \"png/jpeg\")");
            }
            String fileName = fileUtil.saveUploadedFile(avatar, "/images");
            UserDto user = imageDao.findUserByEmail(authentication.getName());
            if (user == null) {
                throw new CanNotFindUserException("User by email " + authentication.getName() + " not found");
            }
            user.setAvatar(fileName);
            imageDao.updateUser(user);
        }
    }
}
