package com.example.demo.services;

import com.example.demo.entitys.ConferenceRoomEntity;
import com.example.demo.repository.ConferenceRoomRepository;
import com.example.demo.repository.CorporationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ConferenceRoomService {
    @Autowired
    ConferenceRoomRepository conferenceRoomRepository;

    @Autowired
    CorporationService corporationService;

    @Autowired
    CorporationRepository corporationRepository;

    public void createRoom(ConferenceRoomEntity conferenceRoom, long corporate_id) {
        conferenceRoom = checkUniqueName(conferenceRoom);
        conferenceRoom.setCorporationEntity(corporationService.getCorporationById(corporate_id));
        conferenceRoomRepository.save(conferenceRoom);
    }

    public ConferenceRoomEntity checkUniqueName(ConferenceRoomEntity conferenceRoomEntity) {
        List<ConferenceRoomEntity> rooms = conferenceRoomRepository.findAll();
        for (ConferenceRoomEntity room : rooms) {
            if (room.getName().equalsIgnoreCase(conferenceRoomEntity.getName())) {
                throw new IllegalArgumentException("Room name already exists");
            }
        }
        return conferenceRoomEntity;
    }

    public List<ConferenceRoomEntity> getAllRooms() {
        return conferenceRoomRepository.findAll();
    }

    public List<ConferenceRoomEntity> getRoomsByCorpoId(long corporateId) {
        if (!corporationRepository.existsById(corporateId)) {
            throw new IllegalArgumentException("Corporation does not exist");
        }
        return conferenceRoomRepository.findByCorporationEntity_Id(corporateId);
    }

    public void deleteRoom(long roomId) {
        if (!conferenceRoomRepository.existsById(roomId)) {
            throw new IllegalArgumentException("Room does not exist");
        } else {
            conferenceRoomRepository.deleteById(roomId);
        }
    }

    public void updateRoom(long roomId, ConferenceRoomEntity conferenceRoomEntity) {
        if (!conferenceRoomRepository.existsById(roomId)) {
            throw new IllegalArgumentException("Room does not exist");
        } else {
            conferenceRoomEntity = checkUniqueName(conferenceRoomEntity);
            ConferenceRoomEntity room = conferenceRoomRepository.findById(roomId).get();
            room.setName(conferenceRoomEntity.getName());
            conferenceRoomRepository.save(room);
        }

    }

    public void deleteAllRoomsByCorporation(long corporateId) {
        List<ConferenceRoomEntity> rooms = conferenceRoomRepository.findByCorporationEntity_Id(corporateId);
        for (ConferenceRoomEntity room : rooms) {
            conferenceRoomRepository.deleteById(room.getId());
        }
    }
}
