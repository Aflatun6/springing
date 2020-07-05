package app.controller;

import app.entity.api_ext.Quote;
import app.service.QuoteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Log4j2
@RestController
@RequestMapping("/quote")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping
    public Quote handle_get(){
        Quote quote = quoteService.obtain();
        log.info(quote);
        log.info(quote.getType());
        log.info(quote.getValue());
        log.info(quote.getValue().getId());
        log.info(quote.getValue().getQuote());
        return quote;
    }

}
