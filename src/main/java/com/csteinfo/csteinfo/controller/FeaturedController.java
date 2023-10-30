package com.csteinfo.csteinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.csteinfo.csteinfo.repository.FeaturedRepo;
import com.csteinfo.csteinfo.service.FeaturedService;

@Controller
public class FeaturedController {
    
    @Autowired
    private FeaturedRepo featuredRepo;

    @Autowired
    private FeaturedService featuredService;

    //add featured event
    @PostMapping("/addFeatured")
    public String addFeatured(@RequestParam("imageFile") MultipartFile imageFile, @RequestParam("caption") String caption) {
        featuredService.saveFileToDB(imageFile, caption);

        return "redirect:/admin";
    }

    //delete featured event
    @GetMapping("/deleteFeatured/{id}")
    public String deleteFeatured(@PathVariable("id") int id) {

        featuredRepo.deleteById(id);

        return "redirect:/admin";
    }

    //update featured event
    @PostMapping("/updateFeatured")
    public String updateFeatured(@RequestParam("newId") int id, @RequestParam("newCaption") String newCaption, @RequestParam("newImageFile") MultipartFile newImageFile) {

        featuredService.updateFileToDB(newImageFile, newCaption, id);

        return "redirect:/admin";
    }
}
