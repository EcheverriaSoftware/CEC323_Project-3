package model;

import java.util.*;

import jakarta.persistence.*;

@Entity(name = "Department")

public class Department {
    @Id
    @Column(name = "department_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentID;

    @Column(length = 128, unique = true)
    private String name;
    
    @Column(length = 8, unique = true)
    private String abbreviation;
    
    @OneToMany(mappedBy = "department")
    private List<Course> courses;
    
    public Department(){}

    public Department(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
        courses = new ArrayList<Course>();
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course c)
    {
        this.courses.add(c);
        c.setDepartment(this);
    }

}
