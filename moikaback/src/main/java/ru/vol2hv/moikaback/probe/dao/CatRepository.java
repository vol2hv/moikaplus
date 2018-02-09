package ru.vol2hv.moikaback.probe.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vol2hv.moikaback.probe.model.Cat;

import java.util.List;

@RepositoryRestResource()
public interface CatRepository extends CrudRepository<Cat, Long> {
    // @RestResource(path = "names") http://localhost:8080/people/search/names
    List<Cat> findByName(@Param("name") String name);
}