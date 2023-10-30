package com.csteinfo.csteinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csteinfo.csteinfo.model.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
    
}
