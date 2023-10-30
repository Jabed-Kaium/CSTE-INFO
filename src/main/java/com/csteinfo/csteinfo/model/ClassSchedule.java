package com.csteinfo.csteinfo.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Component
public class ClassSchedule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String batchName;
    private String imageName;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getBatchName() {
        return batchName;
    }
    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }
    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
