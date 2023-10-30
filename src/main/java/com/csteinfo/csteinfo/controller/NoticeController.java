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

import com.csteinfo.csteinfo.model.Notice;
import com.csteinfo.csteinfo.repository.NoticeRepo;
import com.csteinfo.csteinfo.service.NoticeService;

@Controller
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    
    @Autowired
    private NoticeRepo noticeRepo;

    //show notice
    @GetMapping("/showNotice")
    public String showNotice(Model model) {

        List<Notice> notices = noticeRepo.findAll();
        model.addAttribute("notices", notices);

        return "showNotice.html";
    }

    //add notice
    @PostMapping("/addNotice")
    public String addNotice(@RequestParam("imageFile") MultipartFile imageFile, @RequestParam("description") String description) {
        noticeService.saveFileToDB(imageFile, description);

        return "redirect:/admin";
    }

    //delete notice
    @GetMapping("/deleteNotice/{id}")
    public String deleteBook(@PathVariable("id") int id) {

        noticeRepo.deleteById(id);

        return "redirect:/admin";
    }

    //update notice
    @PostMapping("/updateNotice")
    public String updateNotice(@RequestParam("newId") int id, @RequestParam("newImageFile") MultipartFile newImageFile, @RequestParam("newDescription") String newDescription) {

        noticeService.updateFileToDB(newImageFile, newDescription, id);

        return "redirect:/admin";
    }
}
