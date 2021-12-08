package model;

import java.util.*;

import jakarta.persistence.*;
//Find PK
//Implement the Prerequiste relationship
//Needs constructors and setters/getters
//Multi column unqiue restraint
//Check the setters; only non-primary key fields require setter methods
//Needs ID class 
//Check mapping
@Entity(name = "Course")
public class Course {
    //Need to make multi-col unique key for number and department_id
    @Column(length = 8)
    private String number;
    
    @Column(length = 64)
    private String title;

    // A course is likely to be within the range of values specifiable by a byte
    // Expected high is 5 units
    private byte units;

    @Id
    @Column(name = "course_id") 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseID;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "prerequisite")
    private Set<Prerequisite> prerequisites;

    public Course(){

    }

    public Course(Department department, String number, String title, byte units)
    {
        this.department = department;
        this.number = number;
        this.title = title;
        this.units = units;

        prerequisites = new HashSet<Prerequisite>();
    }


    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public byte getUnits() { return units; }
    public void setUnits(byte units) { this.units = units; }

    public Set<Prerequisite> getPrerequisites() { return prerequisites; }
    public void setPrerequisites(Set<Prerequisite> prerequisites) { this.prerequisites = prerequisites; }
    public void addPrerequisite(Prerequisite p)
    {
        this.prerequisites.add(p);
        p.setPrecedes(this);
    }
}
