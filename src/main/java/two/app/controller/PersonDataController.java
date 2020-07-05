package two.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import two.app.entity.Person;
import two.app.service.PersonService;

import java.util.List;

@RestController
public class PersonDataController {

    private final PersonService personService;


    public PersonDataController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping("/data/p")
    public List<Person> handle() {
        return personService.getAll();
    }
}
