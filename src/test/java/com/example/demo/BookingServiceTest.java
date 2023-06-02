package com.example.demo;

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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Timestamp;
import java.util.List;

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
        testCorporation = corporationService.getCorporationById(1);
        ConferenceRoomEntity conferenceRoomEntity = new ConferenceRoomEntity();
        conferenceRoomEntity.setName("Conference Room One");
        conferenceRoomRepository.save(conferenceRoomEntity);
        List<ConferenceRoomEntity> conferenceRoomEntityList = conferenceRoomService.getAllRooms();
        testingRoom =  conferenceRoomEntityList.get(0);
        System.out.println(testingRoom.getId());
        System.out.println(testCorporation.getId());
    }



    @Test
    public void testGetAllBookings() {

        List<BookingEntity> list = bookingRepository.findAll();
        assertEquals(0,  list.size());
    }

    @Test
    @Transactional
    public void testAddBookingInThePast() {
        // Retrieve the conference room entity in a transactional method
        ConferenceRoomEntity conferenceRoomEntity = getConferenceRoom();

        // Rest of the test code
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setStartTime(Timestamp.valueOf("2021-01-01 12:00:00"));
        bookingEntity.setEndTime(Timestamp.valueOf("2021-01-01 13:00:00"));
        //bookingController.addBooking(bookingEntity, 1);
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
        // Retrieve the conference room entity in a transactional method
        ConferenceRoomEntity conferenceRoomEntity = getConferenceRoom();

        // Rest of the test code
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setStartTime(Timestamp.valueOf("2024-01-01 12:00:00"));
        bookingEntity.setEndTime(Timestamp.valueOf("2024-01-01 13:00:00"));
        bookingController.addBooking(bookingEntity, 1);
        assertEquals(bookingRepository.findAll().size(), 1);
        };
    }




/*    @Test
    public void shouldUserAddCorporationWithNameTooShort(){
        CorporationEntity corporationEntity = new CorporationEntity();
        corporationEntity.setName("te");
        //corporationController.addCorporation(corporationEntity);
        assertThrows(IllegalArgumentException.class, () -> {
            corporationService.createCorporation(corporationEntity);
        });
    }

    @Test
    public void shouldUserAddCorporationWithExistingName(){
        CorporationEntity corporationEntity = new CorporationEntity();
        corporationEntity.setName("Test exiting name");
        corporationController.addCorporation(corporationEntity);
        assertThrows(IllegalArgumentException.class, () -> {
            corporationService.createCorporation(corporationEntity);
        });
    }

    @Test
    public void shouldUserAddCorporationWithExistingNameDifferentCase(){
        CorporationEntity corporationEntity = new CorporationEntity();
        corporationEntity.setName("Test different case");
        corporationController.addCorporation(corporationEntity);
        corporationEntity.setName("test different case");
        assertThrows(IllegalArgumentException.class, () -> {
            corporationService.createCorporation(corporationEntity);
        });
    }

    @Test
    public void shouldUserNeedCorporationID(){
        long corporationID = 1;
        CorporationEntity corporationEntity = new CorporationEntity();
        corporationEntity.setName("GetCorprationID");
        corporationController.addCorporation(corporationEntity);
        CorporationEntity result = corporationController.getCorporationById(corporationID);
        assertEquals(corporationID, result.getId());
    }

    @Test
    public void shouldUserNeedCorporationIDThatDontExist(){
        long corporationID = 11111;
        assertThrows(IllegalArgumentException.class, () -> {
            corporationService.getCorporationById(corporationID);
        });
    }*/



