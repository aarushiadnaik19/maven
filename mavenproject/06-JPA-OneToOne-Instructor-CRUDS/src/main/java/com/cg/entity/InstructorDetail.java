package com.cg.entity;

import javax.persistence.*;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
 
    private String qualification;
 
    public InstructorDetail() {}
 
    public InstructorDetail(String qualification) {
        this.qualification = qualification;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getQualification() {
        return qualification;
    }
 
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}