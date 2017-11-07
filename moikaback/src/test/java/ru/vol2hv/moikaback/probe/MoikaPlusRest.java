package ru.vol2hv.moikaback.probe;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import ru.vol2hv.moikaback.entity.json.CityRestAll;

class RestFull{
    CityRestAll _embedde = new CityRestAll();
}
public class MoikaPlusRest {

    public static void main(String[] args) {
        String url = "http://localhost:8080";
        // авторизация на Rest cервере
        TestRestTemplate testRestTemplate = new TestRestTemplate("user", "111");
        ResponseEntity<String> response = testRestTemplate.getForEntity(url+"/cities", String.class);
        ResponseEntity<RestFull> response1 = testRestTemplate.getForEntity(url+"/cities",RestFull.class);
    }
}
