package com.csteinfo.csteinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csteinfo.csteinfo.model.Featured;

@Repository
public interface FeaturedRepo extends JpaRepository<Featured, Integer>{
    
}
