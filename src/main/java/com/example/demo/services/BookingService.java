package com.example.demo.services;

import com.example.demo.entitys.BookingEntity;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.ConferenceRoomRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ConferenceRoomRepository conferenceRoomRepository;

    public void addBooking(BookingEntity booking, long room_id) {
        if (booking.getStartTime().toLocalDateTime().isAfter(booking.getEndTime().toLocalDateTime())){
            System.out.println(booking.getStartTime().toLocalDateTime());
            System.out.println(booking.getEndTime().toLocalDateTime());
            throw new IllegalArgumentException("Start time must be before end time");
        } else {if (conferenceRoomRepository.existsById(room_id)) {
            int length = 10;
            boolean useLetters = true;
            boolean useNumbers = true;
            String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
            List<BookingEntity> bookingEntities = bookingRepository.findAll();
            for (BookingEntity bookingEntity : bookingEntities) {
                if (bookingEntity.getUniqueId().equals(generatedString)) {
                    generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
                }
            }
            booking.setUniqueId(generatedString);
            booking.setConferenceRoomEntity(conferenceRoomRepository.findById(room_id).get());
            bookingRepository.save(booking);
            throw new IllegalArgumentException("Booking created. Your reference number is: " + bookingRepository.findById(booking.getId()).get().getUniqueId());
        }}


    }


}
