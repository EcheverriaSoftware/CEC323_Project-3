package model;

import java.util.*;

import jakarta.persistence.*;

import java.time.LocalTime;
//Get PK
//Set unique key
//Create getters 
//Create mapping
@Entity(name = "Timeslot")
public class TimeSlot {
    // bitmap
    private byte daysOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    @Id
    @Column(name = "timeslot_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int timeSlotID;

    public TimeSlot(){

    }

    public TimeSlot(byte daysOfWeek, LocalTime startTime, LocalTime endTime) {
        this.daysOfWeek = daysOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public byte getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(byte daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getTimeSlotID() {
        return timeSlotID;
    }
}
