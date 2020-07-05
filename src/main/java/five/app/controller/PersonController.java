package five.app.controller;

import five.app.entity.Person;
import five.app.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    private static String fmt(String s, Object... as) {
        return String.format(s, as);
    }

    @GetMapping("/1")
    public Person getPerson() {
        return new Person(1, "Jim");
    }

    @PostMapping("/1")
    public Person postPerson(@RequestBody Person person) {
        log.info(fmt("posted person -> ", person.toString()));
        return personService.persist(person);
    }

    @GetMapping("id/{id}")
    public Person getById(@PathVariable int id) {
        return personService.findById(id).orElseThrow(RuntimeException::new);
    }


}
