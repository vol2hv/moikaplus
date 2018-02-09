package ru.vol2hv.moikaback;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import ru.vol2hv.moikaback.probe.model.Cat;
import ru.vol2hv.moikaback.probe.model.City;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(City.class);    // Разрешить выдавать в ответе ID
        config.exposeIdsFor(Cat.class);
    }
}