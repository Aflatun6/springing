package five.app.service;

import five.app.entity.Person;
import five.app.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person persist (Person p) {
        return personRepository.save(p);
    }

    public Optional<Person> findById(int id){
        return personRepository.findById(id);
    }
}
