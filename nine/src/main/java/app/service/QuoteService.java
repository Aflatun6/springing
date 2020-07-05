package app.service;

import app.entity.api_ext.Quote;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteService {

    private final RestTemplate restTemplate;

    public QuoteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String obtain_for_object() {
        String url = "https://gturnquist-quoters.cfapps.io/api/random";
        String obj = restTemplate.getForObject(url, String.class);
        return obj;
    }

    public String obtain_for_entity() {
        String url = "https://gturnquist-quoters.cfapps.io/api/random";
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        return entity.getBody();
    }


    public Quote obtain() {
        String url = "https://gturnquist-quoters.cfapps.io/api/random";
        Quote quote = restTemplate.getForObject(url, Quote.class);
        return quote;
    }
}
