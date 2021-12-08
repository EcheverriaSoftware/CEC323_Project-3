//import java.util.List;
//import java.util.Scanner;

import jakarta.persistence.*;
import model.*;
public class App {
    private static EntityManager em;

    public static void main(String[] args) throws Exception {
        // basicDemos();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("CECS 323 Project 3");
        em = factory.createEntityManager();

        initDB();

        System.out.println("Go check DataGrip if you don't believe me!");
        em.close();
    }

    public static void initDB()
    {
        em.getTransaction().begin();
        // Semesters
        /*
        Semester sp2021 = new Semester();
        Semester fa2021 = new Semester();
        Semester sp2022 = new Semester();
        em.persist(sp2021);
        em.persist(fa2021);
        em.persist(sp2022);
        */

        // Departments
        Department cecs = new Department("Computer Engineering and Computer Science", "CECS");
        Department ital = new Department("Italian Studies", "ITAL");

        em.persist(cecs);
        em.persist(ital);

        // Courses
        Course cecs174 = new Course(cecs, "174", "Introduction to Programming and Problem Solving", (byte) 3);

        Prerequisite p174 = new Prerequisite(cecs174, 'C');
        Course cecs274 = new Course(cecs, "274", "Data Structures", (byte) 3);
        cecs274.addPrerequisite(p174);

        Course cecs277 = new Course(cecs, "277", "Object Oriented Application Programming", (byte) 3);
        cecs277.addPrerequisite(p174);

        Prerequisite p274 = new Prerequisite(cecs274, 'C');
        Prerequisite p277 = new Prerequisite(cecs277, 'C');
        Course cecs282 = new Course(cecs, "282", "Advanced C++", (byte) 3);
        cecs282.addPrerequisite(p274);
        cecs282.addPrerequisite(p277);

        Course ital101A = new Course(ital, "101A", "Fundamentals of Italian", (byte) 4);

        Prerequisite p101A = new Prerequisite(ital101A, 'D');
        Course ital101B = new Course(ital, "101B", "Fundamentals of Italian", (byte) 4);
        ital101B.addPrerequisite(p101A);

        em.persist(cecs174);
        em.persist(p174);
        em.persist(cecs274);
        em.persist(p274);
        em.persist(cecs277);
        em.persist(p277);
        em.persist(cecs282);
        em.persist(ital101A);
        em.persist(p101A);
        em.persist(ital101B);

        // TimeSlots
        /*
        TimeSlot MW = new TimeSlot(40, LocalTime.of(12, 30), LocalTime.of(13, 45));
        TimeSlot TuTh = new TimeSlot(20, LocalTime.of(14, 0), LocalTime.of(15, 15));
        TimeSlot MWF = new TimeSlot(42, LocalTime.of(12, 0), LocalTime.of(12, 50));
        TimeSlot F = new TimeSlot(2, LocalTime.of(8, 00), LocalTime.of(10, 45);

        em.persist(MW);
        em.persist(TuTh);
        em.persist(MWF);
        em.persist(F);
        */

        // Sections
        /*
        Section cecs174s1 = new Section()
         */

        em.getTransaction().commit();
    }
}
