package com.example.demo.services;

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
            corporationRepository.save(corporation);

    }

    public List<CorporationEntity> getAllCorporations() {
        return corporationRepository.findAll();
    }

    public Optional<CorporationEntity> getCorporationById(long id) {
        return corporationRepository.findById(id);
    }
}
