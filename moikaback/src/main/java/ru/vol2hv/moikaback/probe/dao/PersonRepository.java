package ru.vol2hv.moikaback.probe.dao;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vol2hv.moikaback.probe.model.Person;

@RepositoryRestResource()
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
    // @RestResource(path = "names") http://localhost:8080/people/search/names
}