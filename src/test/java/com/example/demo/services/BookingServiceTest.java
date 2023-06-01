package com.example.demo.services;
import com.example.demo.entitys.BookingEntity;
import com.example.demo.entitys.ConferenceRoomEntity;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.ConferenceRoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private ConferenceRoomRepository conferenceRoomRepository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void shouldBookingCreationBeSuccessful() {
        BookingEntity booking = new BookingEntity();
        booking.setStartTime(Timestamp.valueOf(LocalDateTime.parse("2023-01-01T10:00:00")));
        booking.setEndTime(Timestamp.valueOf(LocalDateTime.parse("2023-01-01T12:00:00")));

        long roomId = 1;

        ConferenceRoomEntity conferenceRoom = new ConferenceRoomEntity();

        when(conferenceRoomRepository.existsById(roomId)).thenReturn(true);
        when(conferenceRoomRepository.findById(roomId)).thenReturn(Optional.of(conferenceRoom));
        when(bookingRepository.findAll()).thenReturn(new ArrayList<>());
        when(bookingRepository.save(booking)).thenReturn(booking);

        BookingEntity result = bookingService.addBooking(booking, roomId);

        assertNotNull(result);
        assertEquals(booking, result);
        verify(bookingRepository).save(booking);
    }
    @Test
    void shouldBookingBeInThePast() {
        ConferenceRoomEntity conferenceRoom = new ConferenceRoomEntity();
        BookingEntity newBooking = new BookingEntity();
        newBooking.setStartTime(Timestamp.valueOf(LocalDateTime.parse("2022-01-01T10:00:00")));
        newBooking.setEndTime(Timestamp.valueOf(LocalDateTime.parse("2022-01-01T12:00:00")));

        long roomId = 1;
        conferenceRoom.setId(roomId);

        when(conferenceRoomRepository.existsById(roomId)).thenReturn(true);
        when(conferenceRoomRepository.findById(roomId)).thenReturn(Optional.of(conferenceRoom));
        List<BookingEntity> bookings = new ArrayList<>();
        BookingEntity booking2 = new BookingEntity();
        booking2.setStartTime(Timestamp.valueOf(LocalDateTime.parse("2023-01-01T10:00:00")));
        booking2.setEndTime(Timestamp.valueOf(LocalDateTime.parse("2023-01-01T12:00:00")));
        booking2.setConferenceRoomEntity(conferenceRoom);
        newBooking.setConferenceRoomEntity(conferenceRoom);
        bookings.add(booking2);
        when(bookingRepository.findAll()).thenReturn(bookings);



        assertThrows(IllegalArgumentException.class, () -> bookingService.addBooking(newBooking, roomId));
    }





}
