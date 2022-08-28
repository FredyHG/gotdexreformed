package dev.dracarys.gotdexreformed.client;
import dev.dracarys.gotdexreformed.dtos.CharacterDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j
public class IceAndFireClient {

    private final WebClient webClient;

    public IceAndFireClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://thronesapi.com/api/v2").build();
    }

    public Mono<CharacterDto> findAndCharacterById(String id) {
        log.info("Buscando o personagem com o id [{}]", id);
        return webClient
                .get()
                .uri("/Characters/" + id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CharacterDto.class);
    }
    public Flux<CharacterDto> getAllCharacters() {
        log.info("Listando todos os personagens");
        return webClient
                .get()
                .uri("/Characters" )
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(CharacterDto.class);

    }

}

