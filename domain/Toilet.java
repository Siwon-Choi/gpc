package com.sparta.project.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.FileReader;
import java.io.Reader;



@NoArgsConstructor // 기본생성자를 만듭니다.
//겟함수 직접 작성 안해도 쓰게 후ㅐ준다.
@Getter
@Setter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
//time stamped이거 왜쓰는지는 모르겠지만 상속시켜줬다.
public class Toilet extends Timestamped { // -> 생성, 수정시간 자동 할당 함수 사용 가능)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column
    private Long id;

    //entity 그 테이블에서 열이 Address과 content 두줄이 있다? 이말인듯 nullable이 false인걸로 보아
    //무조건 있어야되는건가? 싶음
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String x_wgs84;

    @Column(nullable = false)
    private String y_wgs84;

    @Column(nullable = false)
    private String center_x1;

    @Column(nullable = false)
    private String center_y1;

    @Column(nullable = false)
    private int star;


    @Column(nullable = false)
    private int player;


    //이건 뭐 간단하게 생성자인거지 this이용해서 초기화
    public Toilet(String name, String x_wgs84, String y_wgs84, String center_x1, String center_y1, int star, int player) {
        this.name = name;
        this.x_wgs84 = x_wgs84;
        this.y_wgs84 = y_wgs84;
        this.center_x1 = center_x1;
        this.center_y1 = center_y1;
        this.star = star;
        this.player = player;
    }



    //이건 ToiletRequestDto이용해서 초기화 이게 차이가 아마 위에꺼는 직접적으로 Address이랑 content 줘서 새로 선언할때 초기화시켜주는거고
    //ToiletRequestDto를 쓰는건 아에 쌔삥을 만드는게 아니라 ToiletRequestDto로 기존꺼 이용해서 무언가 data처리를 해주기위해서 쓰는거임
    ///private느낌나게 직접 data에 접근하면 안되니까 아마 ToiletRequestDto쓰는듯
    public Toilet(ToiletRequestDto requestDto) {
        this.name = requestDto.getName();
        this.x_wgs84 = requestDto.getX_wgs84();
        this.y_wgs84 = requestDto.getY_wgs84();
        this.center_x1 = requestDto.getCenter_x1();
        this.center_y1 = requestDto.getCenter_y1();
        this.star = requestDto.getStar();
        this.player = requestDto.getPlayer();
    }


    public void updatestars(ToiletRequestDto requestDto) {
        this.name = this.name;
        this.x_wgs84 = this.x_wgs84;
        this.y_wgs84 = this.y_wgs84;
        this.center_x1 = this.center_x1;
        this.center_y1 = this.center_y1;
        this.star = requestDto.getStar();
        this.player = requestDto.getPlayer();
    }

    public void updateStar(ToiletRequestDto requestDto) {
        this.name = this.name;
        this.x_wgs84 = this.x_wgs84;
        this.y_wgs84 = this.y_wgs84;
        this.center_x1 = this.center_x1;
        this.center_y1 = this.center_y1;
        //this.star += (requestDto.getStar()-this.st;
        this.player = this.player;
    }


    public static void readJson() throws Exception {
        JSONParser parser = new JSONParser();
        // JSON 파일 읽기
        Reader reader = new FileReader("/Users/csw/Desktop/toilet.json");
        JSONArray dateArray = (JSONArray) parser.parse(reader);

        for (int i = 0; i < dateArray.size(); i++) {
            Toilet toilet = new Toilet();

            JSONObject data = (JSONObject) dateArray.get(i);
            toilet.setName((String) data.get("fname"));
            System.out.println((String) data.get("fname"));
        }
    }

}