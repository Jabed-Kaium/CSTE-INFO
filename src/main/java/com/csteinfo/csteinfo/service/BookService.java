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

import com.csteinfo.csteinfo.model.Book;
import com.csteinfo.csteinfo.repository.BookRepo;

@Service
public class BookService {
    
    @Autowired
    private BookRepo bookRepo;
    
    // add file
    public void saveFileToDB(MultipartFile imageFile, MultipartFile file, String bookName) {
        
        Book book = new Book();
        book.setBookName(bookName);
        book.setImageName(imageFile.getOriginalFilename());
        book.setFileName(file.getOriginalFilename());

        Book uploadedBook = bookRepo.save(book);

        if(uploadedBook != null) {
            try {
                
                //for saving book file to 'book' folder
                File saveFile = new ClassPathResource("/static/book").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());

                //for saving book image file to 'book/images' folder
                File saveImageFile = new ClassPathResource("/static/book/images").getFile();
                Path imagePath = Paths.get(saveImageFile.getAbsolutePath()+File.separator+imageFile.getOriginalFilename());
                //System.out.println(path);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // update file
    public void updateFileToDB(MultipartFile imageFile, MultipartFile file, String bookName, int id) {
        
        Book book = bookRepo.getReferenceById(id);
        book.setBookName(bookName);
        book.setImageName(imageFile.getOriginalFilename());
        book.setFileName(file.getOriginalFilename());

        Book uploadedBook = bookRepo.save(book);

        if(uploadedBook != null) {
            try {
                
                //for saving book file to 'book' folder
                File saveFile = new ClassPathResource("/static/book").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());

                //for saving book image file to 'book/images' folder
                File saveImageFile = new ClassPathResource("/static/book/images").getFile();
                Path imagePath = Paths.get(saveImageFile.getAbsolutePath()+File.separator+imageFile.getOriginalFilename());

                //System.out.println(path);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
