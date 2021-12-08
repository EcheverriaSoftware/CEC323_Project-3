package model;

import jakarta.persistence.*;

//Check PK
//Implement relationship
@Entity(name = "Students")
public class Student {
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentID;

    @Column(length = 128)
    private String name;

    public Student(){

    }
}
