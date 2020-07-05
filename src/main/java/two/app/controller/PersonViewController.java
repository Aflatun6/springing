package two.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import two.app.entity.Person;
import two.app.service.PersonService;

import java.util.List;

@Controller
public class PersonViewController {

    private final PersonService personService;

    public PersonViewController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/view/p")
    public String handle(Model model) {
        List<Person> all = personService.getAll();
        model.addAttribute("persons", all);
        return "view_page";

    }
}
