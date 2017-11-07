package ru.vol2hv.moikaback.probe;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vol2hv.moikaback.entity.City;
import ru.vol2hv.moikaback.entity.json.City2;
import ru.vol2hv.moikaback.entity.json.Page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JacksonSample {
    ObjectMapper mapper = new ObjectMapper(); // create once, reuse
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    String  jsonString;
    Page page = new Page(1,2,3,4);

    public static void main(String[] args) {
        JacksonSample jacksonSample = new JacksonSample();

        jacksonSample.jsonCity();

    }

    private void jsonCity() {
        System.out.println("QQ");
        City city = new City(1l,"c1","r1");
        City2 ccc = new City2(city);
        List<City> cityList = new ArrayList<>();
        try {
            jsonString = mapper.writeValueAsString(city);
            city.setName("qqq");
            city = mapper.readValue(jsonString, City.class);
            cityList.add(city);
            cityList.add(new City(2l,"c2","r2"));
            jsonString = mapper.writeValueAsString(cityList);
            jsonString = mapper.writeValueAsString(ccc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
