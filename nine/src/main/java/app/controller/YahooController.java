package app.controller;

import app.entity.api.Stock;
import app.service.YahooService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("yahoo")
public class YahooController {

    private final YahooService yahooService;

    public YahooController(YahooService yahooService) {
        this.yahooService = yahooService;
    }

    @GetMapping
    public List<Stock> get_handle(){
        return yahooService.get();
    }
}
