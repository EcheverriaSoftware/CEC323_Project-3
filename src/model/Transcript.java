package model;

import jakarta.persistence.*;

public class Transcript {
    @Column(length = 2)
    private String gradeEarned;

    public Transcript(){

    }
}

