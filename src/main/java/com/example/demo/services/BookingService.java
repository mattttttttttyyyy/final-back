package com.example.demo.services;

import com.example.demo.entitys.BookingEntity;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.ConferenceRoomRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;


import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ConferenceRoomRepository conferenceRoomRepository;


    public BookingEntity addBooking(BookingEntity booking, long room_id) {
        List<BookingEntity> bookings = bookingRepository.findAll();
        bookings.removeIf(b -> b.getConferenceRoomEntity().getId() != room_id);


        for (BookingEntity b : bookings) {
            if (b.getConferenceRoomEntity().getId() == room_id) {
                if (booking.getStartTime().toLocalDateTime().isAfter(b.getStartTime().toLocalDateTime()) && booking.getStartTime().toLocalDateTime().isBefore(b.getEndTime().toLocalDateTime())) {
                    throw new IllegalArgumentException("Booking start time is in another booking");
                } else if (booking.getEndTime().toLocalDateTime().isAfter(b.getStartTime().toLocalDateTime()) && booking.getEndTime().toLocalDateTime().isBefore(b.getEndTime().toLocalDateTime())) {
                    throw new IllegalArgumentException("Booking end time is in another booking");
                } else if (booking.getStartTime().toLocalDateTime().isBefore(b.getStartTime().toLocalDateTime()) && booking.getEndTime().toLocalDateTime().isAfter(b.getEndTime().toLocalDateTime())) {
                    throw new IllegalArgumentException("Booking is in another booking");
                } else if (booking.getStartTime().toLocalDateTime().isEqual(b.getStartTime().toLocalDateTime()) || booking.getEndTime().toLocalDateTime().isEqual(b.getEndTime().toLocalDateTime())) {
                    throw new IllegalArgumentException("Booking is in another booking");
                }
            }
        }

        if (booking.getStartTime().toLocalDateTime().isBefore( LocalDateTime.now())) {
            throw new IllegalArgumentException("Booking start time is in the past");
        }

        if (booking.getStartTime().toLocalDateTime().isAfter(booking.getEndTime().toLocalDateTime())){
            System.out.println(booking.getStartTime().toLocalDateTime());
            System.out.println(booking.getEndTime().toLocalDateTime());
            throw new IllegalArgumentException("Start time must be before end time");
        }  else {if (conferenceRoomRepository.existsById(room_id)) {
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
            return bookingRepository.save(booking);
            //throw new IllegalArgumentException("Booking created. Your reference number is: " + bookingRepository.findById(booking.getId()).get().getUniqueId());
        }}

        return null;
    }


    public List<BookingEntity> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<BookingEntity> getBookingsByRoom(long roomId) {
        List<BookingEntity> bookings = bookingRepository.findAll();
        bookings.removeIf(booking -> booking.getConferenceRoomEntity().getId() != roomId);
        return bookings;
    }

    public List<BookingEntity> getAvailableToday(long roomId, Timestamp timestamp) {
        List<BookingEntity> bookings = getBookingsByRoom(roomId);
        bookings.removeIf(booking -> !booking.getStartTime().toLocalDateTime().toLocalDate().isEqual(timestamp.toLocalDateTime().toLocalDate()));
        return bookings;
    }

    public void deleteBooking(String uniqueId) {
        List<BookingEntity> bookings = bookingRepository.findAll();
        boolean found = false;
        for (BookingEntity booking : bookings) {
            if (booking.getUniqueId().equals(uniqueId)) {
                bookingRepository.delete(booking);
                found = true;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("Booking not found");
        }

    }

    public List<BookingEntity> getBookingsByDateAndRoom(Date date, long roomId) {
        return bookingRepository.findByStartTimeBetweenAndConferenceRoomEntityId(
                Timestamp.valueOf(date.toLocalDate().atStartOfDay()),
                Timestamp.valueOf(date.toLocalDate().plusDays(1).atStartOfDay()),
                roomId
        );
    }
}
