package ru.vol2hv.moikaback.probe.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.vol2hv.moikaback.probe.model.City;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Commit
//@Rollback
public class CityRepositoryTest {
    @Autowired
    CityRepository cityRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void allTest() throws Exception {
        List<City> cityList;
        logger.info("Логгер это круто!");
        cityRepository.deleteAll();
        cityRepository.save(new City(null, "Новый Оскол", "Белгородская область"));
        cityRepository.save(new City(null, "Воронеж", "Воронежская область"));
        cityList = cityRepository.findByName("Воронеж");
        logger.info(cityList.toString());
    }

}


