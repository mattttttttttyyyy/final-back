package com.example.demo.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "booking")

public class BookingEntity {
    String uniqueId;
    java.sql.Timestamp startTime;
    java.sql.Timestamp endTime;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long id;
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "conference_room_id")
    private ConferenceRoomEntity conferenceRoomEntity;

    public BookingEntity() {
    }

    public ConferenceRoomEntity getConferenceRoomEntity() {
        return conferenceRoomEntity;
    }

    public void setConferenceRoomEntity(ConferenceRoomEntity conferenceRoomEntity) {
        this.conferenceRoomEntity = conferenceRoomEntity;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

}
