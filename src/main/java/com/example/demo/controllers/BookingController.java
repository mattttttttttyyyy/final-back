package com.example.demo.controllers;

import com.example.demo.entitys.BookingEntity;
import com.example.demo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "http://localhost:4200/")

public class BookingController {
    @Autowired
    BookingService service;

    @PostMapping("/add/{room_id}")
    public ResponseEntity<BookingEntity> addBooking(@RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) BookingEntity bookingEntity, @PathVariable long room_id){
        System.out.println(bookingEntity.getStartTime());
        return new ResponseEntity<>(service.addBooking(bookingEntity, room_id), HttpStatus.OK) ;
    }


    @GetMapping("/all")
    public List<BookingEntity> getAllBookings(){
        return service.getAllBookings();
    }

    @GetMapping("/byRoom/{room_id}")
    public List<BookingEntity> getBookingsByRoom(@PathVariable long room_id){
        return service.getBookingsByRoom(room_id);
    }

    @GetMapping("/availableToday/{room_id}")
    public List<BookingEntity> getAvailableToday(@PathVariable long room_id, @RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Timestamp timestamp){
        return service.getAvailableToday(room_id, timestamp);
    }

    @DeleteMapping("/delete/{unique_id}")
    public  ResponseEntity<String> deleteBooking(@PathVariable String unique_id){
        service.deleteBooking(unique_id);
        return new ResponseEntity<>("Booking deleted", HttpStatus.OK);
    }

}
