package com.sparta.project.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // Entity가 자동으로 컬럼으로 인식합니다., 상속의 느낌인듯!!!
@EntityListeners(AuditingEntityListener.class) // 생성/변경 시간을 자동으로 업데이트합니다.
public abstract class Timestamped {  //여기서 abstract로 클래스를 선언했다는 것은 자식 클래스에서 상속해서만 사용 가능하다.)

    @CreatedDate //위의 auditingEntityListner에 반응
    private LocalDateTime createdAt;

    @LastModifiedDate //위의 auditingEntityListner에 반응
    private LocalDateTime modifiedAt;
}
