package com.csteinfo.csteinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.csteinfo.csteinfo.model.Featured;
import com.csteinfo.csteinfo.repository.FeaturedRepo;

@Controller
public class MainController {

    @Autowired
    private FeaturedRepo featuredRepo;
    
    @GetMapping("/")
    public String home(Model model) {
        
        List<Featured> featuredList = featuredRepo.findAll();
        model.addAttribute("featuredList", featuredList);

        return "index.html";
    }

}
