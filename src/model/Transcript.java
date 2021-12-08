package model;

import jakarta.persistence.*;

@Entity(name = "Transcript")
public class Transcript {
    @Id
    @Column(length = 2)
    private String gradeEarned;

    @Id
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @Id
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Transcript(){

    }

    public Transcript(Student student, Section section, String gradeEarned) {
        this.gradeEarned = gradeEarned;
        section.addGrades(this);
        student.addGrades(this);
    }

    public String getGradeEarned() {
        return gradeEarned;
    }

    public void setGradeEarned(String gradeEarned) {
        this.gradeEarned = gradeEarned;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

