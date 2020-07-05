package ten.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Beans {
    @Bean
    public RestTemplate build(){
        System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1");
        return new RestTemplate();
    }
}
