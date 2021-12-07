package model;

import jakarta.persistence.*;
//Implement the Prerequiste relationship
//Needs constructors and setters/getters
//Multi column unqiue restraint
//Check the setters; only non-primary key fields require setter methods
//Needs ID class 
@Entity(name = "Courses")
public class Course {
    //Needs to be unique 
    @Id
    @Column(length = 8)
    private String number;
    @Id
    @Column(length = 64)
    private String title;
    private int units;
    @Id
    @Column(name = "course_id") 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseID;
    //Needs to be unique
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    } 
    
}
