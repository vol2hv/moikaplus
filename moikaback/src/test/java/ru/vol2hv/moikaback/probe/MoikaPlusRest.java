package ru.vol2hv.moikaback.probe;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.web.client.TestRestTemplate;
import ru.vol2hv.moikaback.entity.City;
import ru.vol2hv.moikaback.entity.json.ListEntityDto;

import java.io.IOException;

public class MoikaPlusRest {

    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8080/api/";
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // авторизация на Rest cервере
        TestRestTemplate testRestTemplate = new TestRestTemplate("user", "111");
        String body = testRestTemplate.getForEntity(url + "cities", String.class)
                .getBody();
        System.out.println(body);
        ListEntityDto<City> cities = mapper.readValue(body, ListEntityDto.class);
//        List<City> cityList = mapper.readValue(body, new TypeReference<List<City>>(){});
        cities = testRestTemplate.getForEntity(url + "cities", ListEntityDto.class).getBody();

    }
}
