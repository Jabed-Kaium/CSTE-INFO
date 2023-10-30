package com.csteinfo.csteinfo.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
// import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.Lob;
import jakarta.persistence.Lob;

@Entity
@Component
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // @Lob
    // @Column(columnDefinition = "MEDIUMBLOB")
    // private String image;
    private String name;
    private String designation;
    private String imageName;
    private String degree;
    private String email;
    private String contact;
    private String bloodGroup;
    private String dateOfJoining;
    private String researchInterest;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String description;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getBloodGroup() {
        return bloodGroup;
    }
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }    
    public String getDateOfJoining() {
        return dateOfJoining;
    }
    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }
    public String getResearchInterest() {
        return researchInterest;
    }
    public void setResearchInterest(String researchInterest) {
        this.researchInterest = researchInterest;
    }
    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    // public String getImage() {
    //     return image;
    // }
    // public void setImage(String image) {
    //     this.image = image;
    // }
}
