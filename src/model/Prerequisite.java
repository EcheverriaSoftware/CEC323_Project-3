package model;

import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="Prerequisite")
public class Prerequisite {
    private char minimumGrade;

    @Id
    private Course prerequisite;

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course precedes;

    public Prerequisite(){

    }

    public Prerequisite(Course prerequisite, Course precedes, char minimumGrade) {
        this.minimumGrade = minimumGrade;
        this.prerequisite = prerequisite;
        addPrecedes(precedes);
    }

    public char getMinimumGrade() {
        return minimumGrade;
    }

    public void setMinimumGrade(char minimumGrade) {
        this.minimumGrade = minimumGrade;
    }

    public Course getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(Course prerequisite) {
        this.prerequisite = prerequisite;
    }

    public Course getPrecedes() {
        return precedes;
    }

    public void setPrecedes(Course precedes) {
        this.precedes = precedes;
    }

    public void addPrecedes(Course precedes)
    {
        this.precedes = precedes;
        precedes.getPrerequisites().add(this);
    }
}
