package dev.dracarys.gotdexreformed.controllers;


import dev.dracarys.gotdexreformed.client.ImgBBClient;
import dev.dracarys.gotdexreformed.dtos.ImgBBDto;
import dev.dracarys.gotdexreformed.dtos.ImgDto;
import dev.dracarys.gotdexreformed.models.CharacterEntity;
import dev.dracarys.gotdexreformed.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/webclient")
public class ImgBBController {

    @Autowired
    ImgBBClient imgBBClient;



    final CharacterService characterService;

    public ImgBBController(CharacterService characterService) {
        this.characterService = characterService;
    }


    @PostMapping(
            path = "/upload/",
            consumes = "application/json"
    )
    public CharacterEntity uploadImage (@RequestBody ImgDto imgDto) {
        String image = imgDto.getImage();
        String strID = imgDto.getId();
        Long id = Long.parseLong(strID);
        String name = imgDto.getName();

        Mono<ImgBBDto> s = imgBBClient.uploadImage(image);
        ImgBBDto imgBBDto = s.block();
        if (imgBBDto == null) {
            return null;
        }
        String url = imgBBDto.getData().getUrl();

        if(characterService.getCharByIdGot(id).isEmpty()) {
            CharacterEntity character = auxSave(name,url,id);

            return characterService.save(character);
        }
        if(characterService.getCharByIdGot(id).isPresent()) {
            Optional<CharacterEntity> charac = characterService.getCharByIdGot(id);
            characterService.deleteCharacter(charac.get());
            CharacterEntity character = auxSave(name,url,id);

            return characterService.save(character);
        }

        return  null;
    }



    public CharacterEntity auxSave(String name, String url, Long id) {
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setName(name);
        characterEntity.setId_api_got(id);
        characterEntity.setUrl(url);

        return characterEntity;
    }

}
