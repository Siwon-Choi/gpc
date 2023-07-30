package com.sparta.project.service;//package com.sparta.week03.service;



import com.sparta.project.domain.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
//@RequiredArgsConstructor는 final 혹은 @NotNull이 붙은 필드의 생성자를 자동으로 만들어준다.
//의존성 주입? 뭐 이런거 할때 해주는거
@Service
public class ToiletService {
    //리포지토리에서 뭘 꺼내와야하니까 아마 ToiletServicerepository 이용하는듯
    private final ToiletRepository toiletRepository;
    @Transactional
    public void updatestars(Long id, ToiletRequestDto requestDto) {
        Toilet toilet = toiletRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        //만약 리포지토리에 있어서 memo는 내가 업데이트 하고싶은얘고 이제 새로 업데이트 할 정보가 requestdto이다.
        toilet.updatestars(requestDto);
    }


}