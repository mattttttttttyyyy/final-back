package com.example.demo.services;

import com.example.demo.entitys.BookingEntity;
import com.example.demo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    public void addBooking(BookingEntity booking) {
        bookingRepository.save(booking);

    }
}
