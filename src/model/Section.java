package model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

//Check PK
//Needs helper methods for bidirectional associations
//Check mapping for both semester and timeSlot
@Entity(name = "Section")
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"semester_id, course_id, sectionNumber"})
})
public class Section {
    @Id
    @Column(name = "section_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sectionID;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // Section numbers are relatively small. Personal experience suggests ~5 with the possibility of maybe 20
    // if the college is MASSIVE
    private byte sectionNumber;
    
    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "timeslot_id")
    private TimeSlot timeSlot;

    @ManyToMany
    @JoinTable(
            name = "Enrolled",
            joinColumns = @JoinColumn(name = "section_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> enrolled;

    @OneToMany(mappedBy = "section")
    private Set<Transcript> grades;

    // Capacity could be greater than 127 but also should be less than 32,767
    private short maxCapacity;
    
    public Section(){

    }

    public Section(Course course, byte sectionNumber, Semester semester, TimeSlot timeSlot, short maxCapacity) {
        this.course = course;
        this.sectionNumber = sectionNumber;
        addSemester(semester);
        this.timeSlot = timeSlot;
        this.maxCapacity = maxCapacity;
        this.enrolled = new HashSet<Student>();
        this.grades = new HashSet<Transcript>();
    }

    public int getSectionID() {
        return sectionID;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public byte getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(byte sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public void addSemester(Semester s)
    {
        this.semester = s;
        s.getSections().add(this);
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(short maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Set<Student> getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(Set<Student> enrolled) {
        this.enrolled = enrolled;
    }

    public void addEnrolled(Student s)
    {
        this.enrolled.add(s);
        s.getEnrollment().add(this);
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
        t.setSection(this);
    }
}
