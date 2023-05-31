package com.example.demo.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

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
    @Length(min = 2, max = 20, message = "Name has to be between 2 and 20 characters")
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
