package com.csteinfo.csteinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csteinfo.csteinfo.model.Resource;

@Repository
public interface ResourceRepo extends JpaRepository<Resource, Integer> {
    
}
