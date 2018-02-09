/*
* http://www.studytrails.com/java/json/java-jackson-serialization-list/
* правильной сериализации дочерних объектов не получилось
* биться за нее пока не стал
* */

package ru.vol2hv.moikaback.probe;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

class Animal {
    private String name;

}
@Data
@NoArgsConstructor
class Elephant extends Animal {
    public Elephant(String name) {
        super(name);
    }
    @Override
    public String toString() {
        return "Elephant : " +this.getName();
    }
}

@Data
@NoArgsConstructor
class Lion extends Animal {
     public Lion(String name) {
         super(name);
    }

    @Override
    public String toString() {
        return "Lion: " + this.getName();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Zoo {
    private String nameZoo;
    private String city;
    private List<Animal> animals = new ArrayList<Animal>();
    public Zoo (String nameZoo, String city){
        this.nameZoo = nameZoo;
        this.city = city;
    }
    public void addAnimal (Animal animal){
        animals.add(animal);
    }
}

public class JacksonListSerialization {
    public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
        Zoo zoo = new Zoo("London Zoo", "London");
        Lion lion = new Lion("Simba");
        Elephant elephant = new Elephant("Manny");
        zoo.addAnimal(lion);
        zoo.addAnimal(elephant);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.writeValue(System.out, zoo);
    }
}
