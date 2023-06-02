package com.example.demo.services;

import com.example.demo.entitys.ConferenceRoomEntity;
import com.example.demo.entitys.CorporationEntity;
import com.example.demo.repository.CorporationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CorporationService {
    @Autowired
    CorporationRepository corporationRepository;

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

    public Optional<CorporationEntity> getCorporationById(long id) {
        return corporationRepository.findById(id);
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
}
