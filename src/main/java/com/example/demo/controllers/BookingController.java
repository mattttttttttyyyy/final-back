package com.example.demo.controllers;

import com.example.demo.entitys.BookingEntity;
import com.example.demo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/testing")
@CrossOrigin(origins = "http://localhost:4200/")

public class BookingController {
    @Autowired
    BookingService service;

    @PostMapping("/add")
    public void addBooking(@RequestBody BookingEntity bookingEntity){
        System.out.println(bookingEntity.getStartTime());
        service.addBooking(bookingEntity);
    }

}
