package com.example.demo.services;

import com.example.demo.entitys.ConferenceRoomEntity;
import com.example.demo.entitys.CorporationEntity;
import com.example.demo.repository.ConferenceRoomRepository;
import com.example.demo.repository.CorporationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CorporationService {
    @Autowired
    CorporationRepository corporationRepository;

    @Autowired
    ConferenceRoomRepository conferenceRoomRepository;

    public void createCorporation(CorporationEntity corporation) {
        List<CorporationEntity> corporations = corporationRepository.findAll();
        for (CorporationEntity c : corporations) {
            if (c.getName().equalsIgnoreCase(corporation.getName())) {
                throw new IllegalArgumentException("Corporation name already exists");
            }
        }
        if(corporation.getName().length() < 3){
            throw new IllegalArgumentException("Corporation name too short");
        }
        corporationRepository.save(corporation);
    }

    public List<CorporationEntity> getAllCorporations() {
        return corporationRepository.findAll();
    }

    public CorporationEntity getCorporationById(long id) {
        if (!corporationRepository.existsById(id)) {
            throw new IllegalArgumentException("Corporation does not exist");
        } else {
            return corporationRepository.findById(id).get();
        }
    }

    public int getCorporationByName(String name) {
        List<CorporationEntity> corporations = corporationRepository.findAll();
        for (CorporationEntity c : corporations) {
            if (c.getName().equals(name)) {
                return Math.toIntExact(c.getId());
            }
        }
        return 1;


    }

    public void deleteCorporation(long id) {
        if (!corporationRepository.existsById(id)) {
            throw new IllegalArgumentException("Corporation does not exist");
        } else {
            List<ConferenceRoomEntity> rooms = conferenceRoomRepository.findByCorporationEntity_Id(id);
            for (ConferenceRoomEntity room : rooms) {
                conferenceRoomRepository.deleteById(room.getId());
            }
            corporationRepository.deleteById(id);
        }
    }

    public void updateCorporation(long id, CorporationEntity corporation) {
        if (!corporationRepository.existsById(id)) {
            throw new IllegalArgumentException("Corporation does not exist");
        } else {
            CorporationEntity c = corporationRepository.findById(id).get();
            c.setName(corporation.getName());
            corporationRepository.save(c);
        }
    }
}