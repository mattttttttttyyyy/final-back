package com.example.demo.services;

import com.example.demo.controllers.BookingController;
import com.example.demo.controllers.CorporationController;
import com.example.demo.entitys.BookingEntity;
import com.example.demo.entitys.ConferenceRoomEntity;
import com.example.demo.entitys.CorporationEntity;
import com.example.demo.repository.ConferenceRoomRepository;
import com.example.demo.repository.CorporationRepository;
import com.example.demo.repository.BookingRepository;

import com.example.demo.services.BookingService;
import com.example.demo.services.ConferenceRoomService;
import com.example.demo.services.CorporationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers
@SpringBootTest()
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookingServiceTest {
    @Container
    public static PostgreSQLContainer container  = new PostgreSQLContainer()
            .withUsername("matt")
            .withPassword("test")
            .withDatabaseName("test");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }
    @Autowired
    private  CorporationRepository corporationRepository;

    @Autowired
    private  CorporationService corporationService;

    @Autowired
    private  CorporationController corporationController;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private  ConferenceRoomService conferenceRoomService;

    @Autowired
    private BookingController bookingController;

    @Autowired
    private ConferenceRoomRepository conferenceRoomRepository;

    private  ConferenceRoomEntity testingRoom;

    private  CorporationEntity testCorporation;


    @BeforeEach
    public void corporationSetUp(){
        CorporationEntity corporationEntity = new CorporationEntity();
        corporationEntity.setName("Corporation One");
        corporationService.createCorporation(corporationEntity);
        List<CorporationEntity>  corporationEntityList = corporationService.getAllCorporations();
        testCorporation = corporationService.getCorporationById(corporationEntityList.get(0).getId());
        ConferenceRoomEntity conferenceRoomEntity = new ConferenceRoomEntity();
        conferenceRoomEntity.setName("Conference Room One");
        conferenceRoomRepository.save(conferenceRoomEntity);
        List<ConferenceRoomEntity> conferenceRoomEntityList = conferenceRoomService.getAllRooms();
        testingRoom =  conferenceRoomEntityList.get(0);
        System.out.println(testingRoom.getId());
        System.out.println(testCorporation.getId());
    }

    @AfterEach
    public void tearDown(){
        bookingRepository.deleteAll();
        conferenceRoomRepository.deleteAll();
        corporationRepository.deleteAll();
    }



    @Test
    public void testGetAllBookings() {

        List<BookingEntity> list = bookingRepository.findAll();
        assertEquals(0,  list.size());
    }

    @Test
    @Transactional
    public void testAddBookingInThePast() {
        ConferenceRoomEntity conferenceRoomEntity = getConferenceRoom();

        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setStartTime(Timestamp.valueOf("2021-01-01 12:00:00"));
        bookingEntity.setEndTime(Timestamp.valueOf("2021-01-01 13:00:00"));
        assertThrows(IllegalArgumentException.class, () -> {
            bookingService.addBooking(bookingEntity, 1);
        });
    }

    private ConferenceRoomEntity getConferenceRoom() {
        return conferenceRoomRepository.findAll().get(0);
    }

    @Test
    @Transactional
    public void testAddBooking() {
        ConferenceRoomEntity conferenceRoomEntity = getConferenceRoom();
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setStartTime(Timestamp.valueOf("2024-01-01 12:00:00"));
        bookingEntity.setEndTime(Timestamp.valueOf("2024-01-01 13:00:00"));
        bookingController.addBooking(bookingEntity, conferenceRoomRepository.findAll().get(0).getId());
        assertEquals(bookingRepository.findAll().size(), 1);
        }

    @Test
    @Transactional
    public void testAddBookingWithNoExistingConferenceRoom() {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setStartTime(Timestamp.valueOf("2024-01-01 12:00:00"));
        bookingEntity.setEndTime(Timestamp.valueOf("2024-01-01 13:00:00"));
        assertThrows(NoSuchElementException.class, () -> {
            bookingService.addBooking(bookingEntity, 111111);
        });
    }

    @Test
    @Transactional
    public void shouldUserTryToUpdateBooking() {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setStartTime(Timestamp.valueOf("2050-01-01 12:00:00"));
        bookingEntity.setEndTime(Timestamp.valueOf("2050-01-01 13:00:00"));
        BookingEntity newBooking = bookingController.addBooking(bookingEntity, 1).getBody();
        BookingEntity updatedBooking = new BookingEntity();
        updatedBooking.setStartTime(Timestamp.valueOf("2050-02-01 12:00:00"));
        updatedBooking.setEndTime(Timestamp.valueOf("2050-02-01 13:00:00"));
        assert newBooking != null;
        bookingController.updateBooking(newBooking.getUniqueId(), updatedBooking);
        assertEquals(bookingRepository.findAll().get(0).getStartTime(), Timestamp.valueOf("2050-02-01 12:00:00"));
    }
    }



