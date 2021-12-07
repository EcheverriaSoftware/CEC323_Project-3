package model;

import jakarta.persistence.*;
//Bidirectional relationship w/Course
//Check the setters; only non-primary key fields require setter methods
//Fix the Generated value tag
@Entity
@IdClass(value = departmentPk.class)
public class Department {
    @Id 
    @Column(length = 128, nullable = false)
    private String name;
    @Id 
    @Column(length = 8, nullable = false)
    private String abbreviation;
    @Id
    @Column(name = "department_id", nullable = false) 
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int departmentID;
    public Department(){

    }
    public Department(String name, String abbreviation, int departmentID) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.departmentID = departmentID;
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
    public int getDepartmentID() {
        return departmentID;
    }
    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }
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
