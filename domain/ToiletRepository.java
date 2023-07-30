package com.sparta.project.domain;//package com.sparta.week03.domain;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//public interface MemoRepository extends JpaRepository<Memo, Long> { //JpaRepository에 findByAll이런게 있음
//    //<Memo, Long> - > Memo라는 클래스고, 아이디는 Long인 아이에게 쓸 것이다라는 것
//    List<Memo> findAllByModifiedAtBetweenOrderByModifiedAtDesc(LocalDateTime start, LocalDateTime end);
//    //다 찾아서 수정된 날짜를 기준으로 내림차순으로 정렬을 해줘(최신순)
//}

import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ToiletRepository extends JpaRepository<Toilet, Long> {
    List<Toilet> findAllByModifiedAtBetweenOrderByModifiedAtDesc(LocalDateTime start, LocalDateTime end);
    List<Toilet> findAllByOrderByStarDesc();
    List<Toilet> findAll();

}