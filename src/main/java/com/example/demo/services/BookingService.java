package com.example.demo.services;

import com.example.demo.entities.BookingEntity;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.ConferenceRoomRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ConferenceRoomRepository conferenceRoomRepository;


    public BookingEntity checkBooking(BookingEntity booking, long room_id) {
        try {
            conferenceRoomRepository.findById(room_id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Room doesn't exist");
        }

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

        if (booking.getStartTime().toLocalDateTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Booking start time is in the past");
        }

        if (booking.getStartTime().toLocalDateTime().isAfter(booking.getEndTime().toLocalDateTime())) {
            System.out.println(booking.getStartTime().toLocalDateTime());
            System.out.println(booking.getEndTime().toLocalDateTime());
            throw new IllegalArgumentException("Start time must be before end time");
        }
        System.out.println("inside of name check");

        return booking;
    }


    public BookingEntity addBooking(BookingEntity booking, long room_id) {
        System.out.println("Booking is happening");
        booking = checkBooking(booking, room_id);
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedString;
        boolean idIsUnique = false;

        List<BookingEntity> bookingEntities = bookingRepository.findAll();
        generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        for (BookingEntity bookingEntity : bookingEntities) {
            if (bookingEntity.getUniqueId().equals(generatedString)) {
                generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
            }
        }
        booking.setUniqueId(generatedString);
        booking.setConferenceRoomEntity(conferenceRoomRepository.findById(room_id).get());


        return bookingRepository.save(booking);
    }


    public List<BookingEntity> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<BookingEntity> getBookingsByRoom(long roomId) {
        if (conferenceRoomRepository.existsById(roomId)){  List<BookingEntity> bookings = bookingRepository.findAll();
            bookings.removeIf(booking -> booking.getConferenceRoomEntity().getId() != roomId);
            return bookings;} else {
            throw new IllegalArgumentException("Room doesn't exist");}
    }

    public List<BookingEntity> getAvailableToday(long roomId, Timestamp timestamp) {
        if (conferenceRoomRepository.existsById(roomId)){
            List<BookingEntity> bookings = getBookingsByRoom(roomId);
            bookings.removeIf(booking -> !booking.getStartTime().toLocalDateTime().toLocalDate().isEqual(timestamp.toLocalDateTime().toLocalDate()));
            return bookings;} else {
            throw new IllegalArgumentException("Room doesn't exist");}
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

    public List<BookingEntity> getBookingsByDateAndRoom(String date, long roomId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        System.out.println("Booking request happened");
        Date dateAfter = null;
        try {
            dateAfter = format.parse(date);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<BookingEntity> bookings = bookingRepository.findAll();
        Date finalDateAfter = dateAfter;
        assert finalDateAfter != null;
        LocalDate dateStripped = new Date(finalDateAfter.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        bookings.removeIf(booking -> !booking.getStartTime().toLocalDateTime().toLocalDate().isEqual(dateStripped));

        System.out.println(dateAfter);
        System.out.println(bookings);
        if (bookings.isEmpty()) {
            return null;
        } else {
            return bookings;
        }

    }

    public BookingEntity updateBooking(String uniqueId, BookingEntity bookingEntity) {
        BookingEntity bookingToUpdate = getBookingByUniqueID(uniqueId);
        BookingEntity checkedBooking = checkBooking(bookingEntity, bookingToUpdate.getId());
        bookingToUpdate.setStartTime(checkedBooking.getStartTime());
        bookingToUpdate.setEndTime(checkedBooking.getEndTime());
        bookingRepository.save(bookingToUpdate);
        return bookingRepository.save(bookingToUpdate);
    }


    public BookingEntity getBookingByUniqueID(String uniqueId) {
        List<BookingEntity> bookings = bookingRepository.findAll();
        for (BookingEntity booking : bookings) {
            if (booking.getUniqueId().equals(uniqueId)) {
                return booking;
            }
        }
        throw new IllegalArgumentException("Booking not found");
    }

    public long getRoomIDByUniqueID(String uniqueId) {
        List<BookingEntity> bookings = bookingRepository.findAll();
        for (BookingEntity booking : bookings) {
            if (booking.getUniqueId().equals(uniqueId)) {
                return booking.getConferenceRoomEntity().getId();
            }
        }
        throw new IllegalArgumentException("Booking not found");
    }

    public void deleteBookingById(long id) {
        if (!bookingRepository.existsById(id)) {
            throw new IllegalArgumentException("Booking not found");
        }
        bookingRepository.deleteById(id);
    }
}
