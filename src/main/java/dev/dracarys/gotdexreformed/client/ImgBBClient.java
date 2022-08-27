package dev.dracarys.gotdexreformed.client;


import dev.dracarys.gotdexreformed.dtos.ImgBBDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import static org.springframework.http.MediaType.APPLICATION_JSON;
@Service
@Slf4j
public class ImgBBClient {

    private final WebClient webClient;


    public ImgBBClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://api.imgbb.com/1/upload").build();
    }

    public Mono<ImgBBDto> uploadImage(String imageBase64) {
        log.info("Return url");

        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder.path("/upload")
                        .queryParam("key", "e3a25f926ab8c788e1cf7a4bb2f34926")
                        .build())
                .body(BodyInserters.fromFormData("image", imageBase64))
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("verifique os parametros informados")))
                .bodyToMono(ImgBBDto.class);

    }


}
