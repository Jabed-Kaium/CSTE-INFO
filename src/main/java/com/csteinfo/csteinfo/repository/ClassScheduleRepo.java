package com.csteinfo.csteinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csteinfo.csteinfo.model.ClassSchedule;

@Repository
public interface ClassScheduleRepo extends JpaRepository<ClassSchedule, Integer> {
    
}
