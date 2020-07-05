package app.config;

import app.entity.api.Person;
import app.service.HttpClientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class Autorun {

    private final HttpClientService httpClientService;

    public Autorun(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    @Bean
    public CommandLineRunner auto() {
        return args -> {
            String john = httpClientService.obtain_person_from_remote_as_string(555, "John");
            log.info(john);

            Person jeremy = httpClientService.obtain_person_from_remote_as_person(777, "Jeremy");
            log.info(jeremy);
            log.info(jeremy.getClass());
            log.info(jeremy.getId());
            log.info(jeremy.getName());
        };
    }
}
