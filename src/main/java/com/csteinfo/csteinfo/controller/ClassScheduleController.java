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

import com.csteinfo.csteinfo.model.ClassSchedule;
import com.csteinfo.csteinfo.repository.ClassScheduleRepo;
import com.csteinfo.csteinfo.service.ClassScheduleService;

@Controller
public class ClassScheduleController {
    
    @Autowired
    private ClassScheduleService classScheduleService;
    
    @Autowired
    private ClassScheduleRepo classScheduleRepo;

    //show class schedule
    @GetMapping("/showClassSchedule")
    public String showNotice(Model model) {

        List<ClassSchedule> classSchedules = classScheduleRepo.findAll();
        model.addAttribute("classSchedules", classSchedules);

        return "showClassSchedule.html";
    }

    //add class schedule
    @PostMapping("/addClassSchedule")
    public String addClassSchedule(@RequestParam("imageFile") MultipartFile imageFile, @RequestParam("batchName") String batchName) {
        classScheduleService.saveFileToDB(imageFile, batchName);

        return "redirect:/admin";
    }

    //delete class schedule
    @GetMapping("/deleteClassSchedule/{id}")
    public String deleteClassSchedule(@PathVariable("id") int id) {

        classScheduleRepo.deleteById(id);

        return "redirect:/admin";
    }

    //update class schedule
    @PostMapping("/updateClassSchedule")
    public String updateClassSchedule(@RequestParam("newId") int id, @RequestParam("newImageFile") MultipartFile newImageFile, @RequestParam("newBatchName") String newBatchName) {

        classScheduleService.updateFileToDB(newImageFile, newBatchName, id);

        return "redirect:/admin";
    }
}
