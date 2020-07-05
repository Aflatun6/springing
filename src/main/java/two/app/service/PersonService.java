package two.app.service;

import org.springframework.stereotype.Service;
import two.app.entity.Person;

import java.util.Arrays;
import java.util.List;

@Service
public class PersonService {

    public List<Person> getAll(){
        return Arrays.asList(
                new Person("Jim"),
                new Person("John")
        );
    }

}
