package com.sparta.toiletnearby.controller;

import com.sparta.toiletnearby.domain.Toilet;
import com.sparta.toiletnearby.domain.ToiletRepository;
import com.sparta.toiletnearby.domain.ToiletRequestDto;
import com.sparta.toiletnearby.service.ToiletService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.*;

import java.io.FileReader;
import java.io.Reader;
import java.util.*;

import static com.sparta.toiletnearby.domain.Toilet.distance;

@RequiredArgsConstructor
@RestController
public class ToiletController {
    private final ToiletRepository toiletRepository;
    private final ToiletService toiletService;

    @GetMapping("/api/toilets")
    public List<Toilet> getToilets() throws Exception {
//        LocalDateTime start = LocalDateTime.now().minusDays(1);
//        LocalDateTime end = LocalDateTime.now();
        return toiletRepository.findAll();
    }

    @GetMapping("/api/toiletsNearBy/{R}")
    public List getToiletsNearBy(@PathVariable int R) throws Exception{
        //@RequestParam double user_x, double user_y
        List<Toilet> toilets = toiletRepository.findAll();

        ArrayList array = new ArrayList(){};

        double user_x = 126.986;
        double user_y = 37.541;

        for (int i = 0; i < toilets.size(); i++){
            //Object data = toilets.get(i);
            double x = toilets.get(i).getX_wgs();
            double y = toilets.get(i).getY_wgs();
            double range = distance(x, y, user_x, user_y);
            if (range < R) {
                array.add(toilets.get(i));
            }
        }
        return array;
    }

    @PostMapping("/api/toilets")
    public void createToilet() throws Exception {
        JSONParser parser = new JSONParser();
        // JSON 파일 읽기
        Reader reader = new FileReader("/Users/csw/Desktop/toilet.json");
        JSONArray dateArray = (JSONArray) parser.parse(reader);

        for (int i = 0; i < dateArray.size(); i++) {
            JSONObject data = (JSONObject) dateArray.get(i);
            String s = (String) data.get("fname");
            double x = Double.valueOf((String) data.get("x_wgs84"));
            double y = Double.valueOf((String) data.get("y_wgs84"));
            Toilet toilet = new Toilet(s, x, y, 0, 0, 0);
            toiletRepository.save(toilet);
        }
    }


    @GetMapping("/api/toilet/{TOILET_ID}")
    public Optional<Toilet> getToilet(@PathVariable Long TOILET_ID){
        return toiletRepository.findById(TOILET_ID);
    }


    @PutMapping("/api/toilet/{TOILET_ID}")
    public void AddStar(@PathVariable Long TOILET_ID, @RequestBody ToiletRequestDto requestDto){
        toiletService.postStar(TOILET_ID,requestDto);
    }
}