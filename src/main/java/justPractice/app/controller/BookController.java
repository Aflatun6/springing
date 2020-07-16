package justPractice.app.controller;

import justPractice.app.entity.Book;
import justPractice.app.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class BookController {
    @Autowired
    BookRepo repo;

    @GetMapping("/1")
    @ResponseBody
    public Book handle1() {
        return new Book("python", "me");
    }

    @GetMapping("name/{name}")
    @ResponseBody
    public Book hadnleId(@PathVariable String name) {
        return new Book(name, "me");
    }

    @GetMapping("form")
    public String handleForm() { return "justPractice/form";
    }

    @PostMapping("form")
    public RedirectView handleFormPost(@RequestParam("name") String name, @RequestParam("author") String author) {
        repo.save(new Book(name, author));
        return new RedirectView("form");
    }
}
