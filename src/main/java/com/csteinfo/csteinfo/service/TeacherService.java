package com.csteinfo.csteinfo.service;

import java.io.File;
// import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
// import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
// import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.csteinfo.csteinfo.model.Teacher;
import com.csteinfo.csteinfo.repository.TeacherRepo;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepo teacherRepo;
    
    // add teacher
    public void saveTeacherToDB(MultipartFile file, String name, String designation, String degree, String email, String contact, String bloodGroup, String dateOfJoining, String researchInterest, String description) {
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setDesignation(designation);
        teacher.setDegree(degree);
        teacher.setEmail(email);
        teacher.setContact(contact);
        teacher.setBloodGroup(bloodGroup);
        teacher.setDateOfJoining(dateOfJoining);
        teacher.setResearchInterest(researchInterest);
        teacher.setDescription(description);
        teacher.setImageName(file.getOriginalFilename());
        Teacher uploadedTeacher = teacherRepo.save(teacher);

        if(uploadedTeacher != null) {
            try {
                //for saving teacher image to 'images/teacher-images' folder
                File saveFile = new ClassPathResource("/static/images/teacher-images").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());

                //System.out.println(path);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        
        // String filename = StringUtils.cleanPath(file.getOriginalFilename());
        // if(filename.contains("..")){
        //     System.out.println("Not a valid file");
        // }
        // try {
        //     teacher.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        // teacher.setName(name);
        // teacher.setDesignation(designation);
        // teacherRepo.save(teacher);
    }

    // update teacher
    public void updateTeacherToDB(MultipartFile file, String name, String designation, String degree, String email, String contact, String bloodGroup, String dateOfJoining, String researchInterest, String description, int id) {

        Teacher teacher = teacherRepo.getReferenceById(id);
        teacher.setName(name);
        teacher.setDesignation(designation);
        teacher.setDegree(degree);
        teacher.setEmail(email);
        teacher.setContact(contact);
        teacher.setBloodGroup(bloodGroup);
        teacher.setDateOfJoining(dateOfJoining);
        teacher.setResearchInterest(researchInterest);
        teacher.setDescription(description);
        teacher.setImageName(file.getOriginalFilename());
        Teacher uploadedTeacher = teacherRepo.save(teacher);

        if(uploadedTeacher != null) {
            try {
                //for saving teacher image to 'images/teacher-images' folder
                File saveFile = new ClassPathResource("/static/images/teacher-images").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());

                //System.out.println(path);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // String filename = StringUtils.cleanPath(file.getOriginalFilename());

        // if(filename.contains("..")){
        //     System.out.println("Not a valid file");
        // }
        // try {
        //     teacher.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        // teacher.setName(name);
        // teacher.setDesignation(designation);
        // teacherRepo.save(teacher);
    }
}
