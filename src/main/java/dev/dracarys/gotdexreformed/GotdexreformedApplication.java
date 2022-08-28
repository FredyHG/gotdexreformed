package dev.dracarys.gotdexreformed;

import dev.dracarys.gotdexreformed.imageService.ImagemImgrImpl;
import dev.dracarys.gotdexreformed.imageService.ImagemService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class GotdexreformedApplication {


    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8080")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public ImagemService imagemService(WebClient.Builder builder) {
        return new ImagemImgrImpl(builder);
    }

    public static void main(String[] args) {
        SpringApplication.run(GotdexreformedApplication.class, args);
    }

}
