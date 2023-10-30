package com.csteinfo.csteinfo.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.csteinfo.csteinfo.model.Resource;
import com.csteinfo.csteinfo.repository.ResourceRepo;

@Service
public class ResourceService {
    
    @Autowired
    private ResourceRepo resourceRepo;
    
    // add file
    public void saveFileToDB(MultipartFile imageFile, MultipartFile file, String resourceName) {
        
        Resource resource = new Resource();
        resource.setResourceName(resourceName);
        resource.setImageName(imageFile.getOriginalFilename());
        resource.setFileName(file.getOriginalFilename());
        Resource uploadedResource = resourceRepo.save(resource);

        if(uploadedResource != null) {
            try {
                
                //for saving resource file to 'resource' folder
                File saveFile = new ClassPathResource("/static/resource").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());

                //for saving resource image file to 'resource/images' folder
                File saveImageFile = new ClassPathResource("/static/resource/images").getFile();
                Path imagePath = Paths.get(saveImageFile.getAbsolutePath()+File.separator+imageFile.getOriginalFilename());

                //System.out.println(path);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // update file
    public void updateFileToDB(MultipartFile imageFile, MultipartFile file, String resourceName, int id) {
        
        Resource resource = resourceRepo.getReferenceById(id);
        resource.setResourceName(resourceName);
        resource.setImageName(imageFile.getOriginalFilename());
        resource.setFileName(file.getOriginalFilename());
        Resource uploadedResource = resourceRepo.save(resource);

        if(uploadedResource != null) {
            try {
                
                //for saving resource file to 'resource' folder
                File saveFile = new ClassPathResource("/static/resource").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());

                //for saving resource file to 'resource/images' folder
                File saveImageFile = new ClassPathResource("/static/resource/images").getFile();
                Path imagePath = Paths.get(saveImageFile.getAbsolutePath()+File.separator+imageFile.getOriginalFilename());

                //System.out.println(path);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
