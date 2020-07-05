package seven.app2.autorun;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import seven.app2.entity.Person;
import seven.app2.entity.Phone;
import seven.app2.repo.PersonRepo;
import seven.app2.repo.PhoneRepo;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

            Set<Phone> jim_phones = Stream.of("111-111", "111-222").map(Phone::new).collect(Collectors.toSet());
            Set<Phone> john_phones = Stream.of("111-333", "111-444").map(Phone::new).collect(Collectors.toSet());

            Person jim = new Person("Jim", jim_phones);
            Person john = new Person("John", john_phones);
            personRepo.saveAll(Arrays.asList(jim, john));

        };
    }
}
