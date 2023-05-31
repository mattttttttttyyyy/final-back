package com.example.demo.entitys;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "corporation")

public class CorporationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long id;


    @OneToMany(mappedBy = "corporationEntity")
    private List<ConferenceRoomEntity> conferenceRoomEntityList;

    public List<ConferenceRoomEntity> getConferenceRoomEntityList() {
        return conferenceRoomEntityList;
    }

    public void setConferenceRoomEntityList(List<ConferenceRoomEntity> conferenceRoomEntityList) {
        this.conferenceRoomEntityList = conferenceRoomEntityList;
    }

    private String name;

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

    public CorporationEntity() {
    }
}
