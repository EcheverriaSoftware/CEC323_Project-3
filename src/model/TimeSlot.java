package model;

import java.util.*;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity(name = "Timeslot")
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"daysOfWeek, startTime, endTime"})
})
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

    public boolean overlap(TimeSlot inputTime)
    {
        // See if there is any intersection between bit flags
        int intersection = this.daysOfWeek & inputTime.getDaysOfWeek();
        if (intersection == 0)
        {
            return false;
        }

        // If the bitflag intersection is non-zero, see if the time overlaps at all

        // |   1 |  |  2   |
        // this starts before input ends AND ends after input starts
        if (this.startTime.compareTo(inputTime.getEndTime()) < 0 && this.endTime.compareTo(inputTime.getStartTime()) > 0 )
        {
            return true;
        }
        // |   2 |  |  1   |
        // input starts before this ends AND ends after this starts
        else if (inputTime.getStartTime().compareTo(this.endTime) < 0 && inputTime.getEndTime().compareTo(this.startTime) > 0)
        {
            return true;
        }
        // |   12   |
        // Start or stop at the same time
        else if (inputTime.getStartTime().compareTo(this.startTime) == 0 || inputTime.getEndTime().compareTo(this.endTime) == 0)
        {
            return true;
        }

        return false;
    }
}
