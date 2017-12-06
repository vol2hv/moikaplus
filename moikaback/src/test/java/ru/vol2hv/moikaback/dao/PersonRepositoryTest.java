package ru.vol2hv.moikaback.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vol2hv.moikaback.entity.Gender;
import ru.vol2hv.moikaback.entity.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
//@Commit
//@Rollback
public class PersonRepositoryTest {
    @Autowired
    PersonRepository personRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Test
    public void allTest() throws Exception {
        logger.info("Логгер это круто!");
        personRepository.deleteAll();
        personRepository.save(new Person(null, "Вова",Gender.MALE));
        personRepository.save(new Person(null, "Таня",Gender.FEMALE));
        logger.info(personRepository.findAll().toString());
    }

}


