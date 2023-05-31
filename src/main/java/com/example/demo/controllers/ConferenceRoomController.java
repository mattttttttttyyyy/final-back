package com.example.demo.controllers;

import com.example.demo.entitys.BookingEntity;
import com.example.demo.entitys.ConferenceRoomEntity;
import com.example.demo.services.BookingService;
import com.example.demo.services.ConferenceRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Parameter;

@RestController
@RequestMapping("/conferenceRoom")
@CrossOrigin(origins = "http://localhost:4200/")

public class ConferenceRoomController {
    @Autowired
    ConferenceRoomService service;

    @PostMapping("/add")
    public void addConferenceRoom(@RequestBody ConferenceRoomEntity conferenceRoomEntity,  @RequestParam(name = "corporate_id") long corporate_id){
        service.createRoom(conferenceRoomEntity, corporate_id);
    }


}
