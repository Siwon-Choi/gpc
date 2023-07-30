package com.sparta.project.controller;


import com.sparta.project.domain.*;
import com.sparta.project.service.MemoService;
import com.sparta.project.service.ToiletService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor //final이 붙은 필드의 생성자를 자동 롬복 해주는 어노테이션
@RestController
@Setter
@Getter
// @Controller의 역할은 Model 객체를 만들어 데이터를 담고 View를 찾는 것이지만,
// @RestController는 단순히 객체만을 반환하고 객체 데이터는 JSON 또는 XML 형식으로 HTTP 응답에 담아서 전송합니다.

//Controller는 client의 request를 dto 의 형태로 받는다 또한 적절한 응답을 DTO의 형태로 반환하는 역할을 한다.
// 즉, 요청과 응답을 관리하는 계층이라고 생각하면 된다.
public class MemoController {
    //repository 쓰는이유:
    //Entitiy에 의해 생성된 DB에 접근하는 매서드(ex)Save fihd all)같은 함수를 이용하기 위해서 쓴다.
    //이 entity가 그 테이블 있잖아 가로 세로 그거 그걸 어쨋든 data를 가지고 이용해야되는거잖아 그러기위해서 이 repository가 필요한것이다.
    //자세한건 repository에서 씀
    private final MemoRepository memoRepository;
    private final MemoService memoService;
    private final ToiletRepository toiletRepository;
    private final ToiletService toiletService;
    //4가지 방법이 있다. get 가져오는거 post 등록하는거 put 업데이트 하는거 delete 지우는거


/*
    @GetMapping("/api/toilet/{TOILET_ID}/memos/{MEMO_ID}")
    public String getMemo(@PathVariable Long TOILET_ID){
        Optional<Memo> memo = memoRepository.findById(TOILET_ID);
        String password = memo.get().getPassword();
        return password;
    }
*/

    @GetMapping("/api/toilet/{TOILET_ID}/memos/{MEMO_ID}")
    public List<Memo> getMemo(@PathVariable Long TOILET_ID, @PathVariable Long MEMO_ID){
        return memoRepository.findByToiletidAndMemoid(TOILET_ID, MEMO_ID);
    }
    //3번 토일렛의 2번째 메모지


    @GetMapping("/api/toilet/{TOILET_ID}/memos")
    public List<Memo> getMemos(@PathVariable long TOILET_ID){
        return memoRepository.findAllByToiletid(TOILET_ID);
    }
    //3번 토일렛의 모든 메모지

    @PostMapping("/api/toilet/memos") //아마 crateMemo의 requestDto로 뭔가 class느낌의 그게 오는데 이걸 Memo 로 생성하고
    //그다음 memo를 memo Repository 함수에 넣어서 보내주는듯
    public Memo createMemo(@RequestBody MemoRequestDto requestDto){
        //새로운 memo 객체 생성후 이걸 repository에서 save함수를 이용하여 저장하는듯
        Memo memo = new Memo(requestDto);
        memo.setMemoid(memoRepository.countByToiletid(requestDto.getToiletid())+1);
        //메모의 메모아이디 설정
        //n번째 토일렛의 몇번째 할당하는거

        Optional<Toilet> optionalToilet = toiletRepository.findById(requestDto.getToiletid());
        optionalToilet.ifPresent(toilet -> {
            ToiletRequestDto toiletRequestDto = new ToiletRequestDto();
            toiletRequestDto.setName(toilet.getName());
            toiletRequestDto.setX_wgs84(toilet.getX_wgs84());
            toiletRequestDto.setY_wgs84(toilet.getY_wgs84());
            toiletRequestDto.setCenter_x1(toilet.getCenter_x1());
            toiletRequestDto.setCenter_y1(toilet.getCenter_y1());
            toiletRequestDto.setStar(toilet.getStar() + requestDto.getGood());
            toiletRequestDto.setPlayer(toilet.getPlayer() + 1);
            toiletService.updatestars(toilet.getId(), toiletRequestDto);
        });
        //생각해보니 위의 초기화 의미 없는듯? 그냥 star랑 player빼고 다 아무값이나 넣어도될듯
        return memoRepository.save(memo);
    }


    @PutMapping("/api/toilet/{TOILET_ID}/memos/{MEMO_ID}")
    //정보를 업데이트 해야하므로 그 주체를 찾기위해서 id를 찾는다 id가 키가 되는데 그 키설정하는게 memo class인가? 있다 id를 쓰려면
    //pathVariable이 있어야된다. 그리고 그 위에서 postmapping처럼 이 그 class 느낌의 그게 오니까 requestdto로 받는다
    //그리고 service에서 정의한 update함수를 이용한다.
    public Long updateMemo(@PathVariable long TOILET_ID, @PathVariable Long MEMO_ID, @RequestBody MemoRequestDto requestDto) {
        //업데이트는 서비스니까 서비스 class를 이용하고 아까 위에서는 새로 만들어서 올려야되니까
        //repository이용해서 save해서 새롭게 db에 올리는듯
        memoService.update(TOILET_ID, requestDto);
        return TOILET_ID;
    }

    @DeleteMapping("/api/toilet/{TOILET_ID}/memos/{MEMO_ID}")
    //지
    public Long deleteMemo(@PathVariable Long MEMO_ID) {
        //repository에서 delete함수 써서 지운다.
        memoRepository.deleteById(MEMO_ID);
        return MEMO_ID;
    }
}