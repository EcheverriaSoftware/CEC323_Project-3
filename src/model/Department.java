package model;

import java.util.*;

import jakarta.persistence.*;
//Check the setters; only non-primary key fields require setter methods
@Entity(name = "Departments")
@IdClass(value = departmentPk.class)
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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((abbreviation == null) ? 0 : abbreviation.hashCode());
        result = prime * result + departmentID;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Department other = (Department) obj;
        if (abbreviation == null) {
            if (other.abbreviation != null)
                return false;
        } else if (!abbreviation.equals(other.abbreviation))
            return false;
        if (departmentID != other.departmentID)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
