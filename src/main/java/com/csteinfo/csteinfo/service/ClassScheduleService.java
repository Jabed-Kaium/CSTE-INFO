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

import com.csteinfo.csteinfo.model.ClassSchedule;
import com.csteinfo.csteinfo.repository.ClassScheduleRepo;

@Service
public class ClassScheduleService {
    
    @Autowired
    private ClassScheduleRepo classScheduleRepo;
    
    // add file
    public void saveFileToDB(MultipartFile imageFile, String batchName) {
        
        ClassSchedule classSchedule = new ClassSchedule();
        classSchedule.setBatchName(batchName);
        classSchedule.setImageName(imageFile.getOriginalFilename());

        ClassSchedule uploadedClassSchedule = classScheduleRepo.save(classSchedule);

        if(uploadedClassSchedule != null) {
            try {
                
                //for saving notice file to 'notice' folder
                File saveFile = new ClassPathResource("/static/class-schedule").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+imageFile.getOriginalFilename());

                //System.out.println(path);
                Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // update file
    public void updateFileToDB(MultipartFile imageFile, String batcName, int id) {
        
        ClassSchedule classSchedule = classScheduleRepo.getReferenceById(id);
        classSchedule.setBatchName(batcName);
        classSchedule.setImageName(imageFile.getOriginalFilename());

        ClassSchedule uploadedClassSchedule = classScheduleRepo.save(classSchedule);

        if(uploadedClassSchedule != null) {
            try {
                
                //for saving book file to 'notice' folder
                File saveFile = new ClassPathResource("/static/class-schedule").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+imageFile.getOriginalFilename());

                //System.out.println(path);
                Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
