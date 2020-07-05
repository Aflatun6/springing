package seven.app2.service;

import org.springframework.stereotype.Service;
import seven.app2.entity.Person;
import seven.app2.repo.PersonRepo;

@Service
public class PersonService {

    PersonRepo personRepo;

    public PersonService(PersonRepo personRepo){
        this.personRepo = personRepo;
    }

    public Person getById(long id){
        return personRepo.findById(id).orElseThrow(RuntimeException::new);
    }

}
