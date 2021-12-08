package model;

import java.util.*;

import java.time.LocalDate;

import jakarta.persistence.*;
//Needs helper methods for bidirectional associations
//Check PK
//Create getters for all 
//Create setters for non-pks 
//Needs helper methods for bidirectional associations
//Check mapping
@Entity(name = "Semester")
public class Semester {
    @Column(length = 16)
    private String title;
    
    private LocalDate startDate;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "semester_id") 
    private int semesterID;
    
    @OneToMany(mappedBy = "semester")
    private List<Section> sections;
    
    public Semester(){

    }
}
