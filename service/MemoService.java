package com.sparta.toiletnearby.service;

import com.sparta.toiletnearby.domain.Memo;
import com.sparta.toiletnearby.domain.MemoRepository;
import com.sparta.toiletnearby.domain.MemoRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
//@RequiredArgsConstructor는 final 혹은 @NotNull이 붙은 필드의 생성자를 자동으로 만들어준다.
//의존성 주입? 뭐 이런거 할때 해주는거
@Service
public class MemoService {
    //리포지토리에서 뭘 꺼내와야하니까 아마 memorepository 이용하는듯
    private final MemoRepository memoRepository;

    @Transactional
    public long update(long toiletid, long memoid, MemoRequestDto requestDto) {
        //업데이트를 할꺼니까 repository에서 그 아이디를 찾아야댐
        //그래서 memorepository에서 id로 찾아서 그 객체를 memo 로 return 해주는거임 그리고 없으면 throw시키기
        Memo memo = memoRepository.findByToiletidAndMemoid(toiletid, memoid).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        //만약 리포지토리에 있어서 memo는 내가 업데이트 하고싶은얘고 이제 새로 업데이트 할 정보가 requestdto이다.
        memo.update(requestDto);
        return memo.getId();
    }
    @Transactional
    public void updatelike(Long toiletid, Long memoid, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findByToiletidAndMemoid(toiletid, memoid).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        //만약 리포지토리에 있어서 memo는 내가 업데이트 하고싶은얘고 이제 새로 업데이트 할 정보가 requestdto이다.
        memo.updatelike();
    }


}