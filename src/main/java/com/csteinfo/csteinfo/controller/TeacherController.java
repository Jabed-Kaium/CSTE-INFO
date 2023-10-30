package com.csteinfo.csteinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.csteinfo.csteinfo.model.Teacher;
import com.csteinfo.csteinfo.repository.TeacherRepo;
import com.csteinfo.csteinfo.service.TeacherService;

@Controller
public class TeacherController {

    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/showTeacher")
    public String showTeacher(Model model) {

        List<Teacher> teachers = teacherRepo.findAll();
        model.addAttribute("teachers", teachers);

        return "showTeacher.html";
    }

    @GetMapping("/viewProfile/{id}")
    public String viewProfile(@PathVariable("id") int id, Model model) {
        Teacher teacher = teacherRepo.getReferenceById(id);
        model.addAttribute("teacher", teacher);

        return "viewProfile.html";
    }

    //add teacher
    @PostMapping("/addTeacher")
    public String save(@RequestParam("tName") String name, @RequestParam("tDesignation") String designation, @RequestParam("degree") String degree, @RequestParam("email") String email, @RequestParam("contact") String contact, @RequestParam("bloodGroup") String bloodGroup, @RequestParam("dateOfJoining") String dateOfJoining, @RequestParam("researchInterest") String researchInterest, @RequestParam("description") String description, @RequestParam("file") MultipartFile file){

        teacherService.saveTeacherToDB(file, name, designation, degree, email, contact, bloodGroup, dateOfJoining, researchInterest, description);

        return "redirect:/admin";
    }

    //delete teacher
    @GetMapping("/deleteTeacher/{id}")
    public String deleteTeacher(@PathVariable("id") int id) {

        teacherRepo.deleteById(id);

        return "redirect:/admin";
    }


    //update teacher
    @PostMapping("/updateTeacher")
    public String updateTeacher(@RequestParam("newId") int id, @RequestParam("newName") String newName, @RequestParam("newDesignation") String newDesignation, @RequestParam("newDegree") String newDegree, @RequestParam("newEmail") String newEmail, @RequestParam("newContact") String newContact, @RequestParam("newBloodGroup") String newBloodGroup, @RequestParam("newDateOfJoining") String newDateOfJoining, @RequestParam("newResearchInterest") String newResearchInterest, @RequestParam("newDescription") String newDescription, @RequestParam("newFile") MultipartFile newFile) {

        teacherService.updateTeacherToDB(newFile, newName, newDesignation, newDegree, newEmail, newContact, newBloodGroup, newDateOfJoining, newResearchInterest, newDescription, id);

        return "redirect:/admin";
    }
}
