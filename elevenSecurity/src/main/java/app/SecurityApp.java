package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://localhost:8081/       - any unregistered
 * http://localhost:8081/guest  - any unregistered
 * http://localhost:8081/home   - any authenticated
 * http://localhost:8081/admin  - authorized with ADMIN role
 * http://localhost:8081/me     - authorized with USER role
 * http://localhost:8081/news   - authorized with any USER or ADMIN role
 *
 * http://localhost:8081/login  - added by Spring Security
 * http://localhost:8081/logout - added by Spring Security
 */
@SpringBootApplication
public class SecurityApp {
  public static void main(String[] args) {
    SpringApplication.run(SecurityApp.class, args);
  }
}
