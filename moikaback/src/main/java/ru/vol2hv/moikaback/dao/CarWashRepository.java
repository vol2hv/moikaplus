package ru.vol2hv.moikaback.dao;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vol2hv.moikaback.entity.CarWash;

@RepositoryRestResource()
public interface CarWashRepository extends PagingAndSortingRepository<CarWash, Long> {

}