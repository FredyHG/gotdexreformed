package dev.dracarys.gotdexreformed.controllers;

import dev.dracarys.gotdexreformed.dtos.CharacterDto;
import dev.dracarys.gotdexreformed.client.IceAndFireClient;
import dev.dracarys.gotdexreformed.models.CharacterEntity;
import dev.dracarys.gotdexreformed.repository.CharacterRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping("/webclient")
public class GotController {

    IceAndFireClient iceAndFireClient;
    CharacterRepository characterRepository;

    @GetMapping("/character/{id}")
    public Mono<CharacterDto> getCharById(@PathVariable String id) {
        return iceAndFireClient.findAndCharacterById(id);
    }

    @GetMapping("/character")
    public List<CharacterDto> getAllChars(){
        Flux<CharacterDto> s =  iceAndFireClient.getAllCharacters();
        List<CharacterDto> lcharacterDto = s.collectList().block();
        Objects.requireNonNull(lcharacterDto).forEach(s1 -> {
            Optional<CharacterEntity> s2 = characterRepository.findByIdGot(s1.getId());
            s2.ifPresent(characterEntity -> s1.setUrl(characterEntity.getUrl()));

        });
        return lcharacterDto;

    }

}
