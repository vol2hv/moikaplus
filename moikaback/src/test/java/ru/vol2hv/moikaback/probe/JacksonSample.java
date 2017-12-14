package ru.vol2hv.moikaback.probe;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
import java.util.List;
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
//        citySample();
        sampleN();

    }

    private static void sampleN() throws IOException {
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        HrefBig hrefBig = new HrefBig("href1",true);
        String jsonString = mapper.writeValueAsString(hrefBig);
        jsonString="{\"href\":\"href2\"}";
        HrefBig hrefBig1 = mapper.readValue(jsonString, HrefBig.class);
    }

    private static void citySample() throws IOException {
        String str1 = "{\n" +
                "    \"self\" : {\n" +
                "      \"href\" : \"http://localhost:8080/cities{?page,size,sort}\",\n" +
                "      \"templated\" : true\n" +
                "    },\n" +
                "    \"profile\" : {\n" +
                "      \"href\" : \"http://localhost:8080/profile/cities\"\n" +
                "    },\n" +
                "    \"search\" : {\n" +
                "      \"href\" : \"http://localhost:8080/cities/search\"\n" +
                "    }\n" +
                "  }";
        String str = "{\n" +
                "  \"_embedded\" : {\n" +
                "    \"cities\" : [ {\n" +
                "      \"name\" : \"city1\",\n" +
                "      \"region\" : \"region1\",\n" +
                "      \"_links\" : {\n" +
                "        \"self\" : {\n" +
                "          \"href\" : \"http://localhost:8080/cities/1\"\n" +
                "        },\n" +
                "        \"city\" : {\n" +
                "          \"href\" : \"http://localhost:8080/cities/1\"\n" +
                "        }\n" +
                "      }\n" +
                "    }, {\n" +
                "      \"name\" : \"city2\",\n" +
                "      \"region\" : \"region2\",\n" +
                "      \"_links\" : {\n" +
                "        \"self\" : {\n" +
                "          \"href\" : \"http://localhost:8080/cities/2\"\n" +
                "        },\n" +
                "        \"city\" : {\n" +
                "          \"href\" : \"http://localhost:8080/cities/2\"\n" +
                "        }\n" +
                "      }\n" +
                "    } ]\n" +
                "  },\n" +
                "  \"_links\" : {\n" +
                "    \"self\" : {\n" +
                "      \"href\" : \"http://localhost:8080/cities{?page,size,sort}\",\n" +
                "      \"templated\" : true\n" +
                "    },\n" +
                "    \"profile\" : {\n" +
                "      \"href\" : \"http://localhost:8080/profile/cities\"\n" +
                "    },\n" +
                "    \"search\" : {\n" +
                "      \"href\" : \"http://localhost:8080/cities/search\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"page\" : {\n" +
                "    \"size\" : 20,\n" +
                "    \"totalElements\" : 2,\n" +
                "    \"totalPages\" : 1,\n" +
                "    \"number\" : 0\n" +
                "  }\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        City city = new City (null,"c","r");
        String jsonString = mapper.writeValueAsString(city);
        CityWithRel CityWithRel = new CityWithRel("c","r",null);
        jsonString = mapper.writeValueAsString(CityWithRel);
        LinksOut out = mapper.readValue(str1, LinksOut.class);
        CityDto  CityDto =  mapper.readValue(str, CityDto.class);
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

    private void simple1() throws IOException {
/*
*  1 Пример из https://github.com/FasterXML/jackson-databind/
*/
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        // чтение
        MyValue myResultObject = mapper.readValue("{\"name\":\"Bob\", \"age\":13}", MyValue.class);
//        MyValue myResultObject = mapper.readValue(new File("data.json"), MyValue.class);
//        MyValue myResultObject = mapper.readValue(new URL("http://some.com/api/entry.json"), MyValue.class);

        // Запись
        myResultObject.name = "Pussy";
        String jsonString = mapper.writeValueAsString(myResultObject);
//        mapper.writeValue(new File("result.json"), myResultObject);
//        byte[] jsonBytes = mapper.writeValueAsBytes(myResultObject);

    }

    private void simple2() throws IOException {
        /*
        *  2 Пример из https://github.com/FasterXML/jackson-databind/
        */
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        // Map с Integer
        Map<String, Integer> integerMap = new HashMap<>();
        integerMap = mapper.readValue("{\"два\":2,\"один\":1,\"три\":3}", Map.class);
        integerMap.put("четыре", 4);
        String jsonString = mapper.writeValueAsString(integerMap);
        // List
        List<String> names = mapper.readValue("[\"Name1\",\"Name2\"]", List.class);
        names.add("Name3");
        jsonString = mapper.writeValueAsString(names);
        // Map с типом пользователя MyValue
        jsonString = "{\"Запись1\":{\"name\":\"Name1\",\"age\":62},\"Запись2\":{\"name\":\"Name2\",\"age\":63}}";
        Map<String, MyValue> results = mapper.readValue(jsonString,
                new TypeReference<Map<String, MyValue>>() {
                });
        results.put("Запись3", new MyValue("Name3", 64));
        jsonString = mapper.writeValueAsString(results);
        // дерево узлов
        ObjectNode root = (ObjectNode) mapper.readTree("{ \"name\" : \"Bob\", \"age\" : 13}");
        String name = root.get("name").asText();
        int age = root.get("age").asInt();
        root.with("other").put("type", "student");
        String json = mapper.writeValueAsString(root);
    }

    private void simple3() throws IOException {
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        JsonFactory f = mapper.getFactory(); // may alternatively construct directly too
// First: write simple JSON output
        File jsonFile = new File("test.json");
        JsonGenerator g = f.createGenerator(jsonFile, JsonEncoding.UTF8);
// write JSON: { "message" : "Hello world!" }
        g.writeStartObject();
        g.writeStringField("message", "Hello world!");
        g.writeEndObject();
        g.close();

// Second: read file back
        JsonParser p = f.createParser(jsonFile);

        JsonToken t = p.nextToken(); // Should be JsonToken.START_OBJECT
        t = p.nextToken(); // JsonToken.FIELD_NAME
        if ((t != JsonToken.FIELD_NAME) || !"message".equals(p.getCurrentName())) {
            // handle error
        }
        t = p.nextToken();
        if (t != JsonToken.VALUE_STRING) {
            // similarly
        }
        String msg = p.getText();
        System.out.printf("My message to you is: %s!\n", msg);
        p.close();
    }

}
