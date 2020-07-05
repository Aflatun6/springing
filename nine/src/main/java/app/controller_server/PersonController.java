package app.controller_server;

import app.entity.api.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    @GetMapping("{id}/{name}")
    public Person getPerson(@PathVariable int id, @PathVariable String name) {
        return new Person(id, name);
    }

}
