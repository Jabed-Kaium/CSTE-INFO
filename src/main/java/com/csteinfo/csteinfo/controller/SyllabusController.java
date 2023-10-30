package com.csteinfo.csteinfo.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.csteinfo.csteinfo.model.Syllabus;
import com.csteinfo.csteinfo.repository.SyllabusRepo;
import com.csteinfo.csteinfo.service.SyllabusService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class SyllabusController {

    @Autowired
    private SyllabusService syllabusService;
    
    @Autowired
    private SyllabusRepo syllabusRepo;

    @GetMapping("/showSyllabus")
    public String showSyllabus(Model model) {

        List<Syllabus> syllabuses = syllabusRepo.findAll();
        model.addAttribute("syllabuses", syllabuses);

        return "showSyllabus.html";
    }

    //add syllabus
    @PostMapping("/addSyllabus")
    public String addSyllabus(@RequestParam("file") MultipartFile file, @RequestParam("batchName") String batchName) {
        syllabusService.saveFileToDB(file, batchName);

        return "redirect:/admin";
    }

    //delete syllabus
    @GetMapping("/deleteSyllabus/{id}")
    public String deleteSyllabus(@PathVariable("id") int id) {

        syllabusRepo.deleteById(id);

        return "redirect:/admin";
    }

    //update syllabus
    @PostMapping("/updateSyllabus")
    public String updateSyllabus(@RequestParam("newId") int id, @RequestParam("newBatchName") String newBatchName, @RequestParam("newFile") MultipartFile newFile) {

        syllabusService.updateFileToDB(newFile, newBatchName, id);

        return "redirect:/admin";
    }

    //download syllabus
    @GetMapping("/downloadSyllabus/{fileName}")
    public String downloadBook(HttpServletResponse response, @PathVariable("fileName") String fileName) throws IOException {

        //file stored at 'target/classes/static/syllabus' folder
        File file = new File("target/classes/static/syllabus/" + fileName);

        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + file.getName();

        response.setHeader(headerKey, headerValue);

        ServletOutputStream outputStream = response.getOutputStream();
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        byte[] buffer = new byte[8192]; //8KB buffer
        int bytesRead = -1;

        while((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outputStream.close();

        return "redirect:/showSyllabus";
    }

}
