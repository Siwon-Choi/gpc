package com.sparta.toiletnearby.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.Reader;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Toilet extends Timestamped {
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    private double x_wgs;

    private double y_wgs;

    private int stars;

    private float starsAve;

    private int player;


    public Toilet(String name, double x, double y, int stars, int player, float starsAve){
        this.name = name;
        this.x_wgs = x;
        this.y_wgs = y;
        this.stars = stars;
        this.player = player;
        this.starsAve = starsAve;
    }

    public Toilet(ToiletRequestDto requestDto){
        this.name = requestDto.getName();
        this.x_wgs = requestDto.getX_wgs();
        this.y_wgs = requestDto.getY_wgs();
        this.stars = requestDto.getStars();
        this.starsAve = requestDto.getStarsAve();
        this.player = requestDto.getPlayer();
    }

    public void updatestars(ToiletRequestDto requestDto) {
        //있어야 하는 이유?
        this.stars = requestDto.getStars();
        this.starsAve = requestDto.getStarsAve();
        this.player = requestDto.getPlayer();
    }

    public void postStar(ToiletRequestDto toiletRequestDto) {
        this.player += 1;
        this.stars = this.stars + toiletRequestDto.getStars();
    }
//받아오는 형식을 좀 toiletRequestDto 말고 다른건 안 될까?

    public static JSONArray readJson() throws Exception {
        ToiletRepository toiletRepository = null;
        ToiletRequestDto requestDto;
        JSONParser parser = new JSONParser();
        // JSON 파일 읽기
        Reader reader = new FileReader("/Users/park/IdeaProjects/toiletNearby/src/main/resources/static/toiletInfo.json");
        JSONArray dateArray = (JSONArray) parser.parse(reader);
        return dateArray;
    }
    public static double distance(double lat1, double lon1, double lat2, double lon2){
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))* Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))*Math.cos(deg2rad(lat2))*Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60*1.1515*1609.344;

        return dist; //단위 meter
    }
    public static double deg2rad(double deg){
        return (deg * Math.PI/180.0);
    }
    //radian(라디안)을 10진수로 변환
    public static double rad2deg(double rad){
        return (rad * 180 / Math.PI);
    }
}
