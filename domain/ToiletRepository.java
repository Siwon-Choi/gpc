package com.sparta.toiletnearby.domain;

import org.json.simple.JSONArray;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.FileReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.List;

public interface ToiletRepository extends JpaRepository<Toilet, Long> {
    List<Toilet> findAllByModifiedAtBetweenOrderByModifiedAtDesc(LocalDateTime start, LocalDateTime end);
    List<Toilet> findAllByOrderByStarsDesc();
    List<Toilet> findAll();
}


