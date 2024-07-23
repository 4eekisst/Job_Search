package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.service.ImageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public String uploadAvatar(MultipartFile file){
        return ImageService.uploadImage(file);
    }

    @Override
    public ResponseEntity<?> download(String name) {
        return ImageService.downloadImage( name, MediaType.IMAGE_JPEG);
    }
}
