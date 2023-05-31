package com.example.demo.repository;

import com.example.demo.entitys.BookingEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.springframework.boot.json.JsonParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Repository

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {


}
