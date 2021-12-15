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
    
    public Semester(){}

    public Semester(String title, LocalDate startDate) {
        this.title = title;
        this.startDate = startDate;

        this.sections = new ArrayList<Section>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getSemesterID() {
        return semesterID;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public void addSection(Section s)
    {
        this.sections.add(s);
        s.setSemester(this);
    }
}
