package com.sparta.toiletnearby.domain;

import com.sparta.toiletnearby.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByModifiedAtBetweenOrderByModifiedAtDesc(LocalDateTime start, LocalDateTime end);

    List<Memo> findAllByOrderByGoodDesc();
    List<Memo> findAllByToiletid(long toiletid);
    Optional <Memo> findByToiletidAndMemoid(long toiletid, long memoid);


    long countByToiletid(long toiletid);
}