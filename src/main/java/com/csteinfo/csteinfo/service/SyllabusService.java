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

import com.csteinfo.csteinfo.model.Syllabus;
import com.csteinfo.csteinfo.repository.SyllabusRepo;

@Service
public class SyllabusService {

    @Autowired
    private SyllabusRepo syllabusRepo;
    
    // add file
    public void saveFileToDB(MultipartFile file, String batchName) {
        
        Syllabus syllabus = new Syllabus();
        syllabus.setBatchName(batchName);
        syllabus.setFileName(file.getOriginalFilename());
        Syllabus uploadedSyllabus = syllabusRepo.save(syllabus);

        if(uploadedSyllabus != null) {
            try {
                
                File saveFile = new ClassPathResource("/static/syllabus").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
                //System.out.println(path);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // update file
    public void updateFileToDB(MultipartFile file, String batchName, int id) {
        
        Syllabus syllabus = syllabusRepo.getReferenceById(id);
        syllabus.setBatchName(batchName);
        syllabus.setFileName(file.getOriginalFilename());
        Syllabus uploadedSyllabus = syllabusRepo.save(syllabus);

        if(uploadedSyllabus != null) {
            try {
                
                File saveFile = new ClassPathResource("/static/syllabus").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
                //System.out.println(path);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
