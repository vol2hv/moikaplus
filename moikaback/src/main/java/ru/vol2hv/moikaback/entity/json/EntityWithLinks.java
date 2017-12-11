package ru.vol2hv.moikaback.entity.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;


class PersonResource extends ResourceSupport {

    String firstname;
    String lastname;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityWithLinks<T> implements Serializable {
    Link link = new Link("http://localhost:8080/something");
    private Long id;
    private String name;
    private String region;
}
