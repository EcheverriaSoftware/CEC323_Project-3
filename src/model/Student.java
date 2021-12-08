package model;

import jakarta.persistence.*;

import java.util.*;

//Check PK
//Implement relationship
@Entity(name = "Student")
public class Student {
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // short is too small to contain a student ID
    private int studentID;

    @Column(length = 128)
    private String name;

    @ManyToMany(mappedBy = "enrolled")
    private Set<Section> enrollment;

    @OneToMany(mappedBy = "student")
    private Set<Transcript> grades;

    public Student(){

    }

    public Student(String name, int studentID) {
        this.studentID = studentID;
        this.name = name;
        this.enrollment = new HashSet<Section>();
        this.grades = new HashSet<Transcript>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Section> getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Set<Section> enrollment) {
        this.enrollment = enrollment;
    }

    public void addEnrollment(Section s)
    {
        enrollment.add(s);
        s.getEnrolled().add(this);
    }

    public Set<Transcript> getGrades() {
        return grades;
    }

    public void setGrades(Set<Transcript> grades) {
        this.grades = grades;
    }
    public void addGrades(Transcript t)
    {
        this.grades.add(t);
        t.setStudent(this);
    }

    public double getGpa()
    {
        int total = 0;
        int count = 0;
        for (Transcript grade: this.grades)
        {
            String grd = grade.getGradeEarned();
            int units = grade.getSection().getCourse().getUnits();
            switch (grd) {
                case "A":
                    total += 4 * units;
                    count += units;
                    break;
                case "B":
                    total += 3 * units;
                    count += units;
                    break;
                case "C":
                    total += 2 * units;
                    count += units;
                    break;
                case "D":
                    total += 1 * units;
                    count += units;
                    break;
                case "F":
                    count += units;
                    break;
                default:
                    break;
            }
        }

        return total/count;
    }
}
