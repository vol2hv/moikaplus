package ru.vol2hv.moikaback.probe;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import ru.vol2hv.moikaback.entity.City;
import ru.vol2hv.moikaback.entity.json.ListEntityDto;

public class MoikaPlusRest {

    public static void main(String[] args) {
        String url = "http://localhost:8080";
        // авторизация на Rest cервере
        TestRestTemplate testRestTemplate = new TestRestTemplate("user", "111");
        ResponseEntity<String> response = testRestTemplate.getForEntity(url + "/api/cities", String.class);
        System.out.println(response.getBody());
        ListEntityDto<City> cities = testRestTemplate.getForEntity(url + "/api/cities", ListEntityDto.class).getBody();
    }
}
