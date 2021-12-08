package model;

import java.util.*;

import jakarta.persistence.*;
//Check PK
//Check the setters; only non-primary key fields require setter methods
//Needs helper methods for bidirectional associations
@Entity(name = "Departments")
//@IdClass(value = departmentPk.class)
//Check mapping
public class Department {
    @Id 
    @Column(length = 128)
    private String name;
    
    @Id 
    @Column(length = 8)
    private String abbreviation;
    
    @Id
    @Column(name = "department_id") 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentID;
    
    @OneToMany(mappedBy = "department")
    private List<Course> courses;
    
    public Department(){

    }
    public Department(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }
    public String getName() {
        return name;
    }
    /*public void setName(String name) {
        this.name = name;
    }*/
    public String getAbbreviation() {
        return abbreviation;
    }
    /*public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }*/
    public int getDepartmentID() {
        return departmentID;
    }
    /*public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }*/
}
