package com.example.demo.entitys;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;

import java.text.SimpleDateFormat;

@Entity
@Table(name = "booking")

public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long id;

    @Timestamp
    private SimpleDateFormat startTime;

    public SimpleDateFormat getStartTime() {
        return startTime;
    }

    public void setStartTime(SimpleDateFormat startTime) {
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookingEntity() {
    }

    /*    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }*/
}
