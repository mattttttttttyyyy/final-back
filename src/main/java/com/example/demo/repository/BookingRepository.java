package com.example.demo.repository;

import com.example.demo.entitys.BookingEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.springframework.boot.json.JsonParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {


    List<BookingEntity> findByStartTimeBetweenAndConferenceRoomEntityId(Timestamp timestamp, Timestamp timestamp1, long roomId);

    List<BookingEntity> findByStartTimeAndConferenceRoomEntity_Id(Date date1, long roomId);
}
