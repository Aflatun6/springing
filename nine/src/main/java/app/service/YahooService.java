package app.service;

import app.entity.api.Stock;
import app.entity.yahoof_finance.YResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class YahooService {

    private final RestTemplate restTemplate;

    public YahooService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Stock> get() {
//        String url = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/get-summary?region=US&lang=en";
//        String v1 = "x-rapidapi-host";
//        String v2 = "x-rapidapi-key";
//        String h1 = new String("apidojo-yahoo-finance-v1.p.rapidapi.com");
//        String h2 = new String("bd3a553b56mshf2b71e2ec8877e3p183ffdjsnc5aea1b8624b");
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(v1,h1);
//        headers.add(v2,h2);
//
//        HttpEntity<Object> rq = new HttpEntity<>(headers);
//
//        ResponseEntity<Result> response = restTemplate.exchange(url, HttpMethod.GET, rq, Result.class);

        String url = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/get-summary?region=US&lang=en";
        // construct headers
        String h1 = "x-rapidapi-host";
        String h2 = "x-rapidapi-key";
        String v1 = new String("apidojo-yahoo-finance-v1.p.rapidapi.com");
        String v2 = new String("72c2ad42demsh0702c1e312fc7b0p1fbd07jsne6b67e7d86d4");
        HttpHeaders headers = new HttpHeaders();
        headers.set(h1, v1);
        headers.set(h2, v2);
        // construct entity to send rq
        HttpEntity<Object> rq = new HttpEntity<>(headers);

        ResponseEntity<YResponse> response = restTemplate.exchange(url, HttpMethod.GET, rq, YResponse.class);

        List<Stock> data = response.getBody()
                .getMarketSummaryResponse()
                .getResult().stream()
                .map(yItem -> new Stock(
                        yItem.getSymbol(),
                        yItem.getRegularMarketPrice().getRaw()))
                .collect(Collectors.toList());

        return data;

    }
}
