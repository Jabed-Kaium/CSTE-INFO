package com.csteinfo.csteinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.csteinfo.csteinfo.model.Book;
import com.csteinfo.csteinfo.model.ClassSchedule;
import com.csteinfo.csteinfo.model.Featured;
import com.csteinfo.csteinfo.model.Notice;
import com.csteinfo.csteinfo.model.Resource;
import com.csteinfo.csteinfo.model.Syllabus;
import com.csteinfo.csteinfo.model.Teacher;
import com.csteinfo.csteinfo.repository.BookRepo;
import com.csteinfo.csteinfo.repository.ClassScheduleRepo;
import com.csteinfo.csteinfo.repository.FeaturedRepo;
import com.csteinfo.csteinfo.repository.NoticeRepo;
import com.csteinfo.csteinfo.repository.ResourceRepo;
import com.csteinfo.csteinfo.repository.SyllabusRepo;
import com.csteinfo.csteinfo.repository.TeacherRepo;

@Controller
public class AdminController {
    
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private SyllabusRepo syllabusRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private ResourceRepo resourceRepo;
    @Autowired
    private NoticeRepo noticeRepo;
    @Autowired
    private ClassScheduleRepo classScheduleRepo;
    @Autowired
    private FeaturedRepo featuredRepo;

    @GetMapping("/admin")
    public String admin(Model teacher, Model syllabus, Model book, Model resource, Model notice, Model classSchedule, Model featured) {
        List<Teacher> teachers = teacherRepo.findAll();
        List<Syllabus> syllabuses = syllabusRepo.findAll();
        List<Book> books = bookRepo.findAll();
        List<Resource> resources = resourceRepo.findAll();
        List<Notice> notices = noticeRepo.findAll();
        List<ClassSchedule> classSchedules = classScheduleRepo.findAll();
        List<Featured> featuredList = featuredRepo.findAll();

        teacher.addAttribute("teachers", teachers);
        syllabus.addAttribute("syllabuses", syllabuses);
        book.addAttribute("books", books);
        resource.addAttribute("resources", resources);
        notice.addAttribute("notices", notices);
        classSchedule.addAttribute("classSchedules", classSchedules);
        featured.addAttribute("featuredList", featuredList);

        return "admin.html";
    }
}
