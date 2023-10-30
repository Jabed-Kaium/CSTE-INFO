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

import com.csteinfo.csteinfo.model.Featured;
import com.csteinfo.csteinfo.repository.FeaturedRepo;

@Service
public class FeaturedService {
    
    @Autowired
    private FeaturedRepo featuredRepo;

    // add file
    public void saveFileToDB(MultipartFile imageFile, String caption) {
        
        Featured featured = new Featured();
        featured.setCaption(caption);
        featured.setImageName(imageFile.getOriginalFilename());

        Featured uploadedFeatured = featuredRepo.save(featured);

        if(uploadedFeatured != null) {
            try {
                
                //for saving featured image file to 'images/slide-images' folder
                File saveImageFile = new ClassPathResource("/static/images/slide-images").getFile();
                Path imagePath = Paths.get(saveImageFile.getAbsolutePath()+File.separator+imageFile.getOriginalFilename());
                //System.out.println(path);
                Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // update file
    public void updateFileToDB(MultipartFile imageFile, String caption, int id) {
        
        Featured featured = featuredRepo.getReferenceById(id);
        featured.setCaption(caption);
        featured.setImageName(imageFile.getOriginalFilename());

        Featured uploadedFeatured = featuredRepo.save(featured);

        if(uploadedFeatured != null) {
            try {
                
                //for saving featured image file to 'images/slide-images' folder
                File saveImageFile = new ClassPathResource("/static/images/slide-images").getFile();
                Path imagePath = Paths.get(saveImageFile.getAbsolutePath()+File.separator+imageFile.getOriginalFilename());

                //System.out.println(path);
                Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    
}
