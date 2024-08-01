package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.errors.CanNotFindImageException;
import kg.attractor.jobsearch.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
@Slf4j
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

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
}
