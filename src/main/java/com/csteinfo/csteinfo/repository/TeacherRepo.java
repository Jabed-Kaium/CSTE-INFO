package com.csteinfo.csteinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csteinfo.csteinfo.model.Teacher;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Integer>{
    
}
