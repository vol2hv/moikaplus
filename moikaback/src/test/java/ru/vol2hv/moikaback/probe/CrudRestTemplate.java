package ru.vol2hv.moikaback.probe;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import ru.vol2hv.moikaback.entity.City;

import java.util.Set;

public class CrudRestTemplate {
    static String url = "http://localhost:8080/api";
    // TestRestTemplate используется для облегчения авторизации
    static TestRestTemplate rest = new TestRestTemplate("user", "111");

    public static void main(String[] args) {
        master();
//        update();
    }

    static void master(){
        // ===1. GET ===
        // основной метод exchange также можно использовань 	getForObject и 	getForEntity
        ResponseEntity<ListFullDto<EntityDto<City>>> listCityDto = rest.exchange(
                url + "/cities", HttpMethod.GET, null,
                new ParameterizedTypeReference<ListFullDto<EntityDto<City>>>() {
                });
        // чтение списка городов в текстовом виде
        ResponseEntity<String> cityListString = rest.getForEntity(url + "/cities", String.class);
        // чтение одного города с преобразованием в POJO объект
        ResponseEntity<EntityDto<City>> cityDto = rest.exchange(
                url + "/cities/1", HttpMethod.GET, null,
                new ParameterizedTypeReference<EntityDto<City>>() {
                });

        // ===2. POST ===
        // основной метод exchange также можно использовань postForObject postForLocation
//        // Создание записей
//            HttpEntity<City> request = new HttpEntity<>(new City(null,"new00","york00"));
//               cityDto = rest.exchange(
//                url + "/cities", HttpMethod.POST, request,
//                new ParameterizedTypeReference<EntityDto<City>>() {
//                });
//        HttpEntity<City> request = new HttpEntity<>(new City(null,"new","york"));
//        City city = rest.postForObject(url + "/cities", request, City.class); // возвращается сама запись
//        request.getBody().setName("old");
//        URI location = rest.postForLocation(url + "/cities", request); // возвращается URL
//        request.getBody().setName("NOTold");
//        ResponseEntity<City> response = rest.exchange(url + "/cities",
//                HttpMethod.POST, request, City.class);  // через exchange

        // ===3. Put ===
        // основной метод exchange также можно использовань  put
//        HttpEntity<City> request = new HttpEntity<>(new City(null,"new44","york44"));
//        cityDto = rest.exchange(
//                url + "/cities/37", HttpMethod.PUT, request,
//                new ParameterizedTypeReference<EntityDto<City>>() {
//                });
//        request.getBody().setName("new55");
//        rest.put(url + "/cities/37",request);

        // ===4. DELETE ===
        // основной метод exchange также можно использовань  DELETE
//        cityDto = rest.exchange(
//                url + "/cities/37", HttpMethod.DELETE, null,
//                new ParameterizedTypeReference<EntityDto<City>>() {
//                });c
//        rest.delete(url + "/cities/34");
        // ===4. Поиск ===
        // основной метод exchange также можно использовань  GET
        listCityDto = rest.exchange(
                "http://localhost:8080/api/cities/search/findByName?name={name}",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<ListFullDto<EntityDto<City>>>() {},
                "NOTold");
    }

    static void auxiliary(){
//        Используйте HEAD для извлечения заголовков
        HttpHeaders httpHeaders = rest.headForHeaders(url + "/cities");
        // Используйте OPTIONS для получения разрешенных операций
        Set<HttpMethod> optionsForAllow = rest.optionsForAllow(url + "/cities");
        HttpMethod[] supportedMethods
                = {HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE};
//        assertTrue(optionsForAllow.containsAll(Arrays.asList(supportedMethods)));
    }

}

