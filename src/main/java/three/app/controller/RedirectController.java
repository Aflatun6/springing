package three.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import three.app.entity.Book;

@Controller
@RequestMapping("/red")
public class RedirectController {
    @GetMapping("/book")
    @ResponseBody
    public Book handle_book(){
        return new Book("Java","John");
    }
    @GetMapping("/a")
    public String red_a(){
        return "redirect:/red/book";
    }

    @GetMapping("/b")
    public RedirectView red_b(){
        return new RedirectView("/red/book");
    }

    @GetMapping("/c")
    public RedirectView red_c(){
        return new RedirectView("/red/b");
    }


}
