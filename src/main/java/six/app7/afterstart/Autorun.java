package six.app7.afterstart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import six.app7.entity.Person;
import six.app7.entity.Phone;
import six.app7.repo.PersonRepo;
import six.app7.repo.PhoneRepo;

import java.util.Arrays;

@Configuration
public class Autorun {

    PersonRepo personRepo;
    PhoneRepo phoneRepo;

    public Autorun(PersonRepo personRepo, PhoneRepo phoneRepo) {
        this.personRepo = personRepo;
        this.phoneRepo = phoneRepo;
    }

    @Bean
    public CommandLineRunner autorun1() {

        return (String... args) -> {
            phoneRepo.save(new Phone("333-111-222"));
            phoneRepo.save(new Phone("333-111-223"));
            phoneRepo.save(new Phone("333-111-226"));

            personRepo.saveAll(Arrays.asList(
                    new Person("Jim"),
                    new Person("John"),
                    new Person("Jeremy")
            ));
        };
    }
}
