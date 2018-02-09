package ru.vol2hv.moikaback.probe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vol2hv.moikaback.probe.dao.CatRepository;
import ru.vol2hv.moikaback.probe.model.Cat;

public class CreationOfCats {
    final Logger logger = LoggerFactory.getLogger(CreationOfCats.class);
    @Autowired
    CatRepository catRepository;

    public static void main(String[] args) {
        CreationOfCats creationOfCats = new CreationOfCats();
        creationOfCats.appendCats();
        creationOfCats.findAllCats();
    }

    private void findAllCats() {
        for (Cat cat : catRepository.findAll()) {
            logger.info(cat.toString());
        }
    }

    private void appendCats() {
        logger.info("Логгер это круто!");
        catRepository.deleteAll();
        for (int i = 0; i < 30; i++) {
            catRepository.save(new Cat(null, "Cat" + i, 3 + 0.1 * i, null, false));
        }

    }
}
