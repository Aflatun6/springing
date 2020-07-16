package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://localhost:8081/book

 */
@SpringBootApplication
public class JwtApp {
  public static void main(String[] args) {
    SpringApplication.run(JwtApp.class, args);
  }
}
