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

import com.csteinfo.csteinfo.model.Notice;
import com.csteinfo.csteinfo.repository.NoticeRepo;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepo noticeRepo;
    
    // add file
    public void saveFileToDB(MultipartFile imageFile, String description) {
        
        Notice notice = new Notice();
        notice.setDescription(description);
        notice.setImageName(imageFile.getOriginalFilename());

        Notice uploadedNotice = noticeRepo.save(notice);

        if(uploadedNotice != null) {
            try {
                
                //for saving notice file to 'notice' folder
                File saveFile = new ClassPathResource("/static/notice").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+imageFile.getOriginalFilename());

                //System.out.println(path);
                Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // update file
    public void updateFileToDB(MultipartFile imageFile, String description, int id) {
        
        Notice notice = noticeRepo.getReferenceById(id);
        notice.setDescription(description);
        notice.setImageName(imageFile.getOriginalFilename());

        Notice uploadedNotice = noticeRepo.save(notice);

        if(uploadedNotice != null) {
            try {
                
                //for saving book file to 'notice' folder
                File saveFile = new ClassPathResource("/static/notice").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+imageFile.getOriginalFilename());

                //System.out.println(path);
                Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
