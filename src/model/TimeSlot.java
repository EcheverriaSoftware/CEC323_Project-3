package model;

import java.util.*;

import jakarta.persistence.*;

import java.time.LocalTime;
//Get PK
//Set unique key
//Create getters 
//Create mapping
@Entity(name = "Timeslots")
public class TimeSlot {
    private byte daysOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    
    @Column(name = "timeslot_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int timeSlotID;

    public TimeSlot(){

    }
}
