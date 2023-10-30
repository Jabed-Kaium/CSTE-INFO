package com.csteinfo.csteinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csteinfo.csteinfo.model.Notice;

@Repository
public interface NoticeRepo extends JpaRepository<Notice, Integer> {
    
}
