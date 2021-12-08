package model;

import java.util.*;

import jakarta.persistence.*;
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"number, department_id"})
})
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

    @OneToMany
    @JoinColumn(name = "course_id")
    private List<Prerequisite> prerequisites;

    public Course(){}

    public Course(Department department, String number, String title, byte units)
    {
        this.department = department;
        department.addCourse(this);
        this.number = number;
        this.title = title;
        this.units = units;

        this.prerequisites = new ArrayList<>();
    }


    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public void addDepartment(Department d)
    {
        this.department = d;
        d.getCourses().add(this);
    }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public byte getUnits() { return units; }
    public void setUnits(byte units) { this.units = units; }

    public List<Prerequisite> getPrerequisites() { return prerequisites; }
    public void setPrerequisites(List<Prerequisite> prerequisites) { this.prerequisites = prerequisites; }
    public void addPrerequisite(Prerequisite p)
    {
        this.prerequisites.add(p);
        p.setPrecedes(this);
    }
}
