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

    public enum RegistrationResult { SUCCESS, ALREADY_PASSED, ENROLLED_IN_SECTION, NO_PREREQUISITES, ENROLLED_IN_ANOTHER, TIME_CONFLICT };

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
    
    public RegistrationResult registerForSection(Section s)
    {
        /*
        The student has already received a "C" or better in the course.
        The student is already enrolled in the section. <---
        The student has not met the course prerequisites.
        The student is enrolled in a different section of that course.
        The student is enrolled in another course section with a time conflict: the sections meet on the same day, with at least 1 minute of overlap in their start and end times.
        */
        Course inputCourse = s.getCourse();

        // ALREADY_PASSED LOGIC
        for(Transcript t : this.grades){
            Course tranCourse = t.getSection().getCourse();

            if (inputCourse.equals(tranCourse))
            {
                String grade = t.getGradeEarned();

                switch (grade)
                {
                    case "A":
                    case "B":
                    case "C":
                        return RegistrationResult.ALREADY_PASSED;
                    default:
                        break;
                }
            }
        }

        // ENROLLED_IN_SECTION LOGIC
        if(s.getEnrolled().contains(this)){
            return RegistrationResult.ENROLLED_IN_SECTION;
        }

        // NO_PREREQUISITES LOGIC
        List<Prerequisite> prerequisite = s.getCourse().getPrerequisites();
        for (Prerequisite p : prerequisite)
        {
            Course prereq = p.getPrerequisite();

            boolean found = false;
            for (Transcript t: this.grades)
            {
                Course tranCourse = t.getSection().getCourse();
                if (tranCourse.equals(prereq))
                {
                    // Check for minimum grade
                    boolean passed = courseIsPassed(t, p);
                    if (!passed)
                    {
                        return RegistrationResult.NO_PREREQUISITES;
                    }
                    else
                    {
                        found = true;
                    }
                    break;
                }
            }

            if (!found)
            {
                System.out.println("Does not have prereq: " + prereq.getTitle() + " " + prereq.getNumber());
                return RegistrationResult.NO_PREREQUISITES;
            }
        }

        // ENROLLED_IN_ANOTHER AND TIME_CONFLICT LOGIC
        for(Section sec : enrollment) {
            Course enrolledCourse = sec.getCourse();
            TimeSlot enrolledTime = sec.getTimeSlot();
            TimeSlot inputTime = s.getTimeSlot();

            if (enrolledCourse.equals(inputCourse)) {
                return RegistrationResult.ENROLLED_IN_ANOTHER;
            }

            if (enrolledTime == inputTime)
            {
                return RegistrationResult.TIME_CONFLICT;
            }
        }
            return RegistrationResult.SUCCESS;
    }

    public boolean courseIsPassed(Transcript t, Prerequisite p)
    {
        int tGrade = gradeToInt(t.getGradeEarned());
        int pGrade = gradeToInt(String.valueOf(p.getMinimumGrade()));

        if (tGrade >= pGrade)
        {
            return true;
        }
        return false;
    }

    public int gradeToInt(String grade)
    {
        switch (grade)
        {
            case "A":
                return 4;
            case "B":
                return 3;
            case "C":
                return 2;
            case "D":
                return 1;
            default:
                return 0;
        }
    }
}