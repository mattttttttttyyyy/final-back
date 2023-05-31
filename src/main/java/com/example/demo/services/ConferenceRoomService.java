package com.example.demo.services;

import com.example.demo.entitys.ConferenceRoomEntity;
import com.example.demo.repository.ConferenceRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConferenceRoomService {
    @Autowired
    ConferenceRoomRepository conferenceRoomRepository;

    @Autowired
    CorporationService corporationService;

    public void createRoom(ConferenceRoomEntity conferenceRoom, long corporate_id) {
        conferenceRoom.setCorporationEntity(corporationService.getCorporationById(corporate_id).get());
        conferenceRoomRepository.save(conferenceRoom);

    }
}
