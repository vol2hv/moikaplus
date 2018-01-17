package ru.vol2hv.moikaback.probe;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForeignsJacksonSample {
    public static void main(String[] args) throws IOException {
        ForeignsJacksonSample jacksonSample = new ForeignsJacksonSample();
        jacksonSample.simple1();
        jacksonSample.simple2();
        jacksonSample.simple3();
    }
    //    Пример из https://github.com/FasterXML/jackson-databind/
    private void simple1() throws IOException {
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
    //    Пример из https://github.com/FasterXML/jackson-databind/
    private void simple2() throws IOException {
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
    // еше какой-то чужой пример
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
