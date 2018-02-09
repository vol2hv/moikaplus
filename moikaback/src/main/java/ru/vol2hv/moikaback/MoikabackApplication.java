package ru.vol2hv.moikaback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*  було первоначально при сождании прокта */
public class MoikabackApplication {
	public static void main(String[] args) {
		SpringApplication.run(MoikabackApplication.class, args);
	}
}

/* вариант созданием котов
public class MoikabackApplication implements CommandLineRunner {
	final Logger logger = LoggerFactory.getLogger( MoikabackApplication.class);
	@Autowired
	CatRepository catRepository;

	public static void main(String[] args) {
		SpringApplication.run(MoikabackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Логгер это круто!");
		catRepository.deleteAll();

		java.sql.Date bday;
		for (int i = 0; i < 30; i++) {
			catRepository.save(new Cat(null, "Cat" + i, 3 + 0.1 * i,
					new java.sql.Date(118, 0,i+1), false));
		}
		for (Cat cat : catRepository.findAll()) {
			logger.info(cat.toString());
		}

	}
}
*/