package seven.app.autorun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import seven.app.entity.Person;
import seven.app.entity.Phone;
import seven.app.repo.PersonRepo;
import seven.app.repo.PhoneRepo;

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
            Person jim = new Person("Jim");
            Person john = new Person("John");
            Person jeremy = new Person("Jeremy");

            Phone phone1 = new Phone("111-111", jim);
            Phone phone2 = new Phone("111-222", jeremy);
            Phone phone3 = new Phone("111-333", jim);
            phoneRepo.saveAll(Arrays.asList(phone1, phone2, phone3));
//            Set<Phone> jim_phones = Stream.of("111-111", "111-222").map(Phone::new).collect(Collectors.toSet());
//            Set<Phone> john_phones = Stream.of("111-333", "111-444").map(Phone::new).collect(Collectors.toSet());
//
//            Person jim = new Person("Jim", jim_phones);
//            Person john = new Person("John", john_phones);
//            personRepo.saveAll(Arrays.asList(jim, john));

        };
    }
}
