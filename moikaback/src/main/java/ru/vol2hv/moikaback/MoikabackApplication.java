package ru.vol2hv.moikaback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(exclude = RepositoryRestMvcAutoConfiguration.class)
public class MoikabackApplication {
	public static void main(String[] args) {
		SpringApplication.run(MoikabackApplication.class, args);
	}
}
