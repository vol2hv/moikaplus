package ru.vol2hv.moikaback.probe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Album {
    private String title;
    private Artist artist;
    private String[] links;
    private List<String> Songs = new ArrayList<>();
    private Map<String, String> musicians = new HashMap<>();

    public void addMusician(String key, String value) {
        musicians.put(key, value);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Artist {
    private String name;
    private Date birthDate;
}

public class JacsonFirstTutor {


    public static void main(String[] args) throws IOException, ParseException {
        JacsonFirstTutor firstTutor = new JacsonFirstTutor();
//        firstTutor.putFromJavaObject();
        firstTutor.createTreeModel();

    }
    private void createTreeModel() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String jsonString = "";
        JsonNode root = mapper.readTree( new File("example.json"));
        // читаем и изменяем поле last
        JsonNode nameNode = root.path("name");
        String last = nameNode.path("last").textValue();
        ((ObjectNode)nameNode).put("last", last+"рус");
        // Добавляем новый ветвистый узел
        ObjectNode addedNode = ((ObjectNode) root).putObject("address");
        addedNode
                .put("city", "Seattle")
                .put("state", "Washington")
                .put("country", "United States")
                .put("my", "qq");
        jsonString = mapper.writeValueAsString(root);
        // Удаление поля
        ((ObjectNode)root.path("address")).remove("my");

        // 6. Create a new ArrayNode and add to root
        ArrayNode gamesNode = mapper.createArrayNode();

        ObjectNode game1 = mapper.createObjectNode();
        game1.put("name", "Fall Out 4");
        game1.put("price", 49.9);

        ObjectNode game2 = mapper.createObjectNode();
        game2.put("name", "Dark Soul 3");
        game2.put("price", 59.9);

        gamesNode.add(game1);
        gamesNode.add(game2);
        ((ObjectNode) root).set("games", gamesNode);

        jsonString = mapper.writeValueAsString(root);
        System.out.println(jsonString);
    }

    private void putFromJavaObject() throws JsonProcessingException, ParseException {
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");
        mapper.setDateFormat(outputFormat);

        Artist artist = new Artist();
        artist.setName("Miles Davis");
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        artist.setBirthDate(format.parse("26-05-1926"));
        Map<String, String> mus = new HashMap<>();
        mus.put ("Miles Davis", "Trumpet, Band leader");
        mus.put("Julian Adderley", "Alto Saxophone");
        mus.put("Paul Chambers", "double bass");

        Album album = new Album("Kind Of Blue", artist, new String[]{"link1", "link2"},
                Arrays.asList(new String[]{"song1", "song1", "song1"}),mus);
        String jsonString = mapper.writeValueAsString(album);
        System.out.println(jsonString);
    }

}
