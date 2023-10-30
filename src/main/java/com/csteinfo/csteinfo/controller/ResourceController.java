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

import com.csteinfo.csteinfo.model.Resource;
import com.csteinfo.csteinfo.repository.ResourceRepo;
import com.csteinfo.csteinfo.service.ResourceService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ResourceController {
    
    @Autowired
    private ResourceService resourceService;
    
    @Autowired
    private ResourceRepo resourceRepo;

    //show resource
    @GetMapping("/showResource")
    public String showResource(Model model) {

        List<Resource> resources = resourceRepo.findAll();
        model.addAttribute("resources", resources);

        return "showResource.html";
    }

    //add resource
    @PostMapping("/addResource")
    public String addResource(@RequestParam("resourceFile") MultipartFile imageFile, @RequestParam("file") MultipartFile file, @RequestParam("resourceName") String resourceName) {
        resourceService.saveFileToDB(imageFile, file, resourceName);

        return "redirect:/admin";
    }

    //delete resource
    @GetMapping("/deleteResouce/{id}")
    public String deleteResource(@PathVariable("id") int id) {

        resourceRepo.deleteById(id);

        return "redirect:/admin";
    }

    //update resource
    @PostMapping("/updateResource")
    public String updateResource(@RequestParam("newId") int id, @RequestParam("newResourceName") String newResourceName, @RequestParam("newResourceImageFile") MultipartFile newResourceImageFile, @RequestParam("newFile") MultipartFile newFile) {

        resourceService.updateFileToDB(newResourceImageFile, newFile, newResourceName, id);

        return "redirect:/admin";
    }

    //download resource
    @GetMapping("/downloadResource/{fileName}")
    public String downloadResource(HttpServletResponse response, @PathVariable("fileName") String fileName) throws IOException {

        //file stored at 'target/classes/static/resource' folder
        File file = new File("target/classes/static/resource/" + fileName);

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

        return "redirect:/showResource";
    }
}
