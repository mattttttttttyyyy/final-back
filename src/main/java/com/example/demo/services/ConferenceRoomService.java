package com.example.demo.services;

import com.example.demo.entitys.ConferenceRoomEntity;
import com.example.demo.repository.ConferenceRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public List<ConferenceRoomEntity> getAllRooms() {
        return conferenceRoomRepository.findAll();
    }

    public List<ConferenceRoomEntity> getRoomsByCorpoId(long corporateId) {
        return conferenceRoomRepository.findByCorporationEntity_Id(corporateId);
    }

    public void deleteRoom(long roomId) {
        conferenceRoomRepository.deleteById(roomId);
    }
}
