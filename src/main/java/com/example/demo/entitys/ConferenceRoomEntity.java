package com.example.demo.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "conference_room")

public class ConferenceRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "corporation_id", columnDefinition = "integer")
    private CorporationEntity corporationEntity;

    @OneToMany(mappedBy = "conferenceRoomEntity")
    private List<BookingEntity> bookingEntityList;

    public List<BookingEntity> getBookingEntityList() {
        return bookingEntityList;
    }

    public void setBookingEntityList(List<BookingEntity> bookingEntityList) {
        this.bookingEntityList = bookingEntityList;
    }

    public CorporationEntity getCorporationEntity() {
        return corporationEntity;
    }

    public void setCorporationEntity(CorporationEntity corporationEntity) {
        this.corporationEntity = corporationEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConferenceRoomEntity() {
    }
}
