package model;

import jakarta.persistence.*;
//Check PK
//Needs helper methods for bidirectional associations
//Check mapping for both semester and timeSlot
@Entity(name = "Sections")
public class Section {
    @Id
    private int sectionNumber;
    
    @Id
    private int maxCapacity;

    @Id
    @Column(name = "section_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sectionID;
    
    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester; 

    @ManyToOne
    @JoinColumn(name = "timeslot_id")
    private TimeSlot timeSlot;
    
    public Section(){

    }
}
