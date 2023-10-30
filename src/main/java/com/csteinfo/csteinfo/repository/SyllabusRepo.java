package com.csteinfo.csteinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csteinfo.csteinfo.model.Syllabus;

@Repository
public interface SyllabusRepo extends JpaRepository<Syllabus, Integer>{
    
}
