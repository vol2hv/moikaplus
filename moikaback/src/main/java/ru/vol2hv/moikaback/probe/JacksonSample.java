package ru.vol2hv.moikaback.probe;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vol2hv.moikaback.probe.model.City;

import java.io.File;
import java.io.IOException;

public class JacksonSample {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) throws IOException {
//        withoutGeneric();
//        EntityWithLinks();
    }

    // без Generic'ов
    private static void withoutGeneric() throws IOException {
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        CityDto citydto = mapper.readValue(new  File("jsoncity.json"), CityDto.class);
        ListFullDto<CityDto> listCityDto = mapper.readValue(new  File("jsonlistfull.json"),
                new TypeReference<ListFullDto<CityDto> >() {});
    }

    // наследование от класса ссвлок (ккорее всего можно обойтись без наследования, используя @JsonPropertyOrder
    private static void EntityWithLinks() throws IOException {
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        EntityDto<City> city = mapper.readValue(new  File("jsoncity.json"),
                new TypeReference< EntityDto<City>>() {});
        ListFullDto<EntityDto<City>> listCityDto = mapper.readValue(new  File("jsonlistfull.json"),
                new TypeReference<ListFullDto<EntityDto<City>>>() {});
    }
}
