package three.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import three.app.entity.Book;

@Controller
@RequestMapping("/fw")
public class ForwardController {
    @GetMapping("/book")
    @ResponseBody
    public Book handle_book() {
        return new Book("Forward", "Alex");
    }

    @GetMapping("/a")
    public String red_a() {
        return "forward:/red/book";
    }

    @GetMapping("/b")
    public String red_b() {
        return "forward:/red/a";
    }


}
