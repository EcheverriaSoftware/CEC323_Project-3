package model;

import jakarta.persistence.*;
//Find PK
//Implement the Prerequiste relationship
//Needs constructors and setters/getters
//Multi column unqiue restraint
//Check the setters; only non-primary key fields require setter methods
//Needs ID class 
//Check mapping
@Entity(name = "Courses")
public class Course {
    //Needs to be unique 
    @Column(length = 8)
    private String number;
    
    @Column(length = 64)
    private String title;
    private int units;
    
    @Column(name = "course_id") 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseID;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    
    public Course(){

    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    } 
    
}
