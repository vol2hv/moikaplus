package ru.vol2hv.moikaback.dao;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vol2hv.moikaback.entity.City;

import java.util.List;

@RepositoryRestResource()
public interface CityRepository extends PagingAndSortingRepository<City, Long> {
    // @RestResource(path = "names") http://localhost:8080/people/search/names
    List<City> findByName(@Param("name") String name);
}