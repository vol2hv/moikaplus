package ru.vol2hv.moikaback.probe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import ru.vol2hv.moikaback.entity.City;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class MyValue {
    public String name;
    public int age;

    public MyValue() {
    }

    public MyValue(String name, int age) {
        this.name = name;
        this.age = age;
    }
    // NOTE: if using getters/setters, can keep fields `protected` or `private`
}

public class JacksonSample {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) throws IOException {
        JacksonSample jacksonSample = new JacksonSample();
//        jacksonSample.simple1();
//        jacksonSample.simple2();
//        jacksonSample.simple3();
//        hateaos();
//        hateaosHttp();
//        sampleN();
//        citySample1();
//        citySample1a();
//        citySample2();
//        citySample3();
        sampleN1();
    }
    private static void sampleN1() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,HrefBig> link = new HashMap<>();
        City city =new City(null, "c1","r1");
        link.put("self",new HrefBig("href1",true));
        Data1<City> data1 = new Data1<City>(link,City.class);
        String jsonString = mapper.writeValueAsString(data1);
    }
// Чтение HrefBig
    private static void sampleN() throws IOException {
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        HrefBig hrefBig = new HrefBig("href1",true);
        String jsonString = mapper.writeValueAsString(hrefBig);
        jsonString="{\"href\":\"href2\"}";
        HrefBig hrefBig1 = mapper.readValue(jsonString, HrefBig.class);
    }
//    чтение ссылок из внутреннего блока _embeded
    private static void citySample1() throws IOException {
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        Map<String, HrefBig> out = mapper.readValue( new File("jsonlinks"), new TypeReference< Map<String, HrefBig>>() {});
    }
    // чтение одной сущности City
    private static void citySample1a() throws IOException {
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        Resource<City> city = mapper.readValue(new  File("jsoncity"), new TypeReference< Resource<City>>() {});
 //       EntityWithLinks<City> city = mapper.readValue(str, new TypeReference< EntityWithLinks<City>>() {});
    }
    private static void citySample2() throws IOException {
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        ListDto<City>  listCityDto =  mapper.readValue(new File("jsoncities"),  new TypeReference<ListDto<City>>() {});
    }
    private static void citySample3() throws IOException{
        ObjectMapper mapper = new ObjectMapper();

    }
    private static void hateaosHttp() {
        String url = "http://localhost:8080";
        TestRestTemplate rest = new TestRestTemplate("user", "111");
        ResponseEntity<String> response = rest.getForEntity(url, String.class);
        ;
        ResponseEntity<Resource<City>> getResult = rest.exchange(
                url +"/cities/1", HttpMethod.GET, null,
                new ParameterizedTypeReference<Resource<City>>() {
                });
    }

    private static void hateaos() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        Link link = new Link("http://localhost:8080/something","MyRel");
        String jsonString = mapper.writeValueAsString(link);
//        {"rel":"MyRel","href":"http://localhost:8080/something"}

        Resource<City> resource = new Resource<>(new City(null,"g1","r1"));
        jsonString = mapper.writeValueAsString(resource);
//        {"id":null,"name":"g1","region":"r1","links":[]}

        resource = new Resource<>(new City(null,"g1","r1"),
                new Link("http://localhost:8080/something","MyRel"),
                new Link("http://localhost:8080/something1","MyRel1"));
        jsonString = mapper.writeValueAsString(resource);
    }
}
