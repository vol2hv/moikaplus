package ru.vol2hv.moikaback.probe;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

public class MoikaPlusRest {

    public static void main(String[] args) {
        String url = "http://localhost:8080";
        // авторизация на Rest cервере
        TestRestTemplate testRestTemplate = new TestRestTemplate("user", "111");
        ResponseEntity<String> response = testRestTemplate.getForEntity(url + "/cities", String.class);
        System.out.println(response.getBody());
//        ResponseEntity<CityDto> cityDto = testRestTemplate.getForEntity(url + "/cities", CityDto.class);
    }
}
