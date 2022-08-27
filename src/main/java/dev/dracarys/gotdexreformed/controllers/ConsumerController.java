package dev.dracarys.gotdexreformed.controllers;


import dev.dracarys.gotdexreformed.client.IceAndFireClient;
import dev.dracarys.gotdexreformed.dtos.CharacterDto;
import dev.dracarys.gotdexreformed.models.CharacterEntity;
import dev.dracarys.gotdexreformed.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/webclient")
public class ConsumerController {

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    IceAndFireClient iceAndFireClient;

    @GetMapping("/character/image")
    public List<CharacterEntity> getAllCharsWithImage(){
        return characterRepository.findAllCharactersWithImage();
    }
    @GetMapping("/character/image/{id}")
    public Mono<CharacterDto> getCharInf(@PathVariable String id) {
        return iceAndFireClient.findAndCharacterById(id);
    }
}
