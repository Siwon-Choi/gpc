package com.sparta.project.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//그 requestdto가 그냥 함부러 멤버변수에 접근하면 안되서 requestdto이용해서 접근하는듯?
/*DTO는 말 그대로 데이터를 Transfer(이동)하기 위한 객체이다.
Client가 Controller에 요청을 보낼 때도 RequestDto의 형식으로 데이터가 이동하고,
Controller가 Client에게 응답을 보낼 때도 ResponseDto의 형태로 데이터를 보내게 된다.
Controller와 Service, Repository 계층 사이에 데이터가 오갈 때도 데이터는 DTO의 형태로 이동하게 된다.
DTO는 로직을 갖고 있지 않는 순수한 데이터 객체이며, 일반적으로 getter/setter 메서드만을 가진다.
하지만 DTO는 단순히 데이터를 옮기는 용도이기 때문에 굳이 Setter를 이용해 값을 수정할 필요가 없이, 생성자만을 사용하여 값을 할당하는 게 좋다. */
public class MemoRequestDto {
    private long toiletid;
    private long memoid;
    private String username;
    private String contents;
    private String password;
    private int good;
}