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

import com.csteinfo.csteinfo.model.Book;
import com.csteinfo.csteinfo.repository.BookRepo;
import com.csteinfo.csteinfo.service.BookService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private BookRepo bookRepo;

    //show book
    @GetMapping("/showBook")
    public String showBook(Model model) {

        List<Book> books = bookRepo.findAll();
        model.addAttribute("books", books);

        return "showBook.html";
    }

    //add book
    @PostMapping("/addBook")
    public String addBook(@RequestParam("imageFile") MultipartFile imageFile, @RequestParam("file") MultipartFile file, @RequestParam("bookName") String bookName) {
        bookService.saveFileToDB(imageFile, file, bookName);

        return "redirect:/admin";
    }

    //delete book
    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id) {

        bookRepo.deleteById(id);

        return "redirect:/admin";
    }

    //update book
    @PostMapping("/updateBook")
    public String updateBook(@RequestParam("newId") int id, @RequestParam("newBookName") String newBookName, @RequestParam("newImageFile") MultipartFile newImageFile, @RequestParam("newFile") MultipartFile newFile) {

        bookService.updateFileToDB(newImageFile, newFile, newBookName, id);

        return "redirect:/admin";
    }

    /*
    //download book
    @GetMapping("/downloadBook")
    public String downloadBook(@Param("id") int id, Model model) {

        Book book = bookRepo.getReferenceById(id);
        model.addAttribute("book", book);

        return "downloadBook.html";
    }
    */

    //download book
    @GetMapping("/downloadBook/{fileName}")
    public String downloadBook(HttpServletResponse response, @PathVariable("fileName") String fileName) throws IOException {

        //file stored at 'target/classes/static/book' folder
        File file = new File("target/classes/static/book/" + fileName);

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

        return "redirect:/showBook";
    }
}
