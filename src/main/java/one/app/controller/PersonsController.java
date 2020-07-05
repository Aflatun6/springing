package one.app.controller;

import one.app.entity.Person;
import one.app.service.CalcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

//@Controller
@RestController
public class PersonsController {

    private final CalcService calcService;

    public PersonsController(CalcService calcService) {
        this.calcService = calcService;
    }

    //    @ResponseBody // OR WE COULD INSTEAD OF @Controller ANNOTATION JUST ABOVE CLASS CREATING PUT @RestContoller
    @RequestMapping("/persons")
    public List<Person> handle() {
        return Arrays.asList(
                new Person("Jim"),
                new Person("John")
        );
    }

    @GetMapping("/calc")
    public String calc() {
        return Integer.toString(calcService.sub(1, 2));
    }

}
