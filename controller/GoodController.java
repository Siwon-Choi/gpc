package com.sparta.project.controller;


import com.sparta.project.domain.MemoRepository;
import com.sparta.project.domain.MemoRequestDto;
import com.sparta.project.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor //final이 붙은 필드의 생성자를 자동 롬복 해주는 어노테이션
@RestController
public class GoodController {
    private final MemoRepository memoRepository;
    private final MemoService memoService;

    @PutMapping("/api/toilet/{TOILET_ID}/memos/{MEMO_ID}/good")
    public void updated(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        memoService.updatelike(id,requestDto);
    }
}