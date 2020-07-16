package app.controller;

import app.entity.api.Book;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("book")
public class BookController {

    @GetMapping
    public List<Book> handleBook(Authentication auth) {
        return Arrays.asList(
                new Book("Java", "Alex"),
                new Book("Scala", "Aflatun")
        );
    }


}
