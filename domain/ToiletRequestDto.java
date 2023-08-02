package com.sparta.toiletnearby.domain;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

@Setter
@Getter
public class ToiletRequestDto {
    private String name;
    private double x_wgs;
    private double y_wgs;
    private int stars;
    private float starsAve;
    private int player;
}

