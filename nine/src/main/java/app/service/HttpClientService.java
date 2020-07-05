package app.service;

import app.entity.api.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpClientService {

    private final RestTemplate restTemplate;

    public HttpClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String obtain_person_from_remote_as_string(int id, String name) {
        String url = String.format("http://localhost:8080/person/%d/%s", id, name);
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        HttpStatus statusCode = entity.getStatusCode();
        String body = entity.getBody();
        return body;
    }

    public Person obtain_person_from_remote_as_person(int id, String name) {
        String url = String.format("http://localhost:8080/person/%d/%s", id, name);
        ResponseEntity<Person> entity = restTemplate.getForEntity(url, Person.class);
        HttpStatus statusCode = entity.getStatusCode();
        Person body = entity.getBody();
        return body;

    }

}
