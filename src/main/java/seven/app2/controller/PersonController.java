package seven.app2.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seven.app2.entity.Person;
import seven.app2.service.PersonService;

@Log4j2
@RestController
@RequestMapping("/")
public class PersonController {

    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping("{id}")
    public Person getPerson(@PathVariable int id){
        return  personService.getById(id);
    }

}
