package dev.dracarys.gotdexreformed.imageService;

import dev.dracarys.gotdexreformed.dtos.ImgBBDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

public class ImagemImgrImpl implements ImagemService {

    private final WebClient webClient;


    public ImagemImgrImpl(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://api.imgur.com/3").build();
    }

    public ImgBBDto uploadImage(String imageBase64) {
        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder.path("/image")
                        .build())
                .body(BodyInserters.fromFormData("image", imageBase64))
                .accept(APPLICATION_JSON)
                .headers(h -> h.setBearerAuth("e04f20bcdaedd6b463804c7acc7b81dcde2b422a"))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("verifique os parametros informados")))
                .bodyToMono(ImgBBDto.class)
                .block();

    }
}
