package com.example.demo.services;

import com.example.demo.entitys.CorporationEntity;
import com.example.demo.repository.CorporationRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CorporationServiceTest {

    @Spy
    @Resource
    @InjectMocks
    CorporationService corporationService;

    @Mock
    CorporationRepository corporationRepository;

    @BeforeEach
    void onInit() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCorporation() {
        CorporationEntity corporationEntity = new CorporationEntity();
        corporationEntity.setId(5L);
        corporationEntity.setName("Test");
        corporationService.createCorporation(corporationEntity);
        verify(corporationRepository).save(corporationEntity);

    }

    @Test
    void getCorporationById() {
        CorporationEntity corporationEntity = new CorporationEntity();
        corporationEntity.setId(1L);
        when(corporationRepository.findById(1L)).thenReturn(java.util.Optional.of(corporationEntity));
    }



}