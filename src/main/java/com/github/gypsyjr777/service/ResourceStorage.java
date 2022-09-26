package com.github.gypsyjr777.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

@Service
public class ResourceStorage {
    @Value("${upload.path}")
    String uploadPath;

    public String saveNewBookImageBySlug(MultipartFile file, String slug) throws IOException {
        String resourceURI = null;

        if (!file.isEmpty()){
            if (!new File(uploadPath).exists()){
                Files.createDirectories(Paths.get(uploadPath));
                Logger.getLogger(this.getClass().getSimpleName()).info("created image folder in " + uploadPath);
            }

            String filename = slug + "." + FilenameUtils.getExtension(file.getOriginalFilename());
            Path path = Paths.get(uploadPath, filename);

            resourceURI = "/fromBookShopApp/" + filename;
            file.transferTo(path);
            Logger.getLogger(this.getClass().getSimpleName()).info("UploadOK");
        }

        return resourceURI;
    }
}
