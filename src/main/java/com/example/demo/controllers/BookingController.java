package com.example.demo.controllers;

import com.example.demo.entitys.BookingEntity;
import com.example.demo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "http://localhost:4200/")

public class BookingController {
    @Autowired
    BookingService service;

    @PostMapping("/add/{room_id}")
    public void addBooking(@RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) BookingEntity bookingEntity, @PathVariable long room_id){
        System.out.println(bookingEntity.getStartTime());
        service.addBooking(bookingEntity, room_id);
    }

}
