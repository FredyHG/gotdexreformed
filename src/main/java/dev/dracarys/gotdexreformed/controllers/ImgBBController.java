package dev.dracarys.gotdexreformed.controllers;


import dev.dracarys.gotdexreformed.client.IceAndFireClient;
import dev.dracarys.gotdexreformed.dtos.CharacterDto;
import dev.dracarys.gotdexreformed.dtos.ImgBBDto;
import dev.dracarys.gotdexreformed.dtos.ImgDto;
import dev.dracarys.gotdexreformed.imageService.ImagemService;
import dev.dracarys.gotdexreformed.models.CharacterEntity;
import dev.dracarys.gotdexreformed.services.CharacterService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/webclient")
public class ImgBBController {

    @Autowired
    ImagemService imagemService;
    @Autowired
    IceAndFireClient iceAndFireClient;


    final CharacterService characterService;

    public ImgBBController(CharacterService characterService) {
        this.characterService = characterService;
    }


    @PostMapping(
            path = "/upload/",
            consumes = "application/json"
    )
    public ResponseEntity<CharacterEntity> uploadImage (@RequestBody ImgDto imgDto) {
        String image = imgDto.getImage();
        String strID = imgDto.getId();
        Long id = Long.parseLong(strID);
        CharacterDto characterDto = iceAndFireClient.findAndCharacterById(id.toString()).block();
        String name = imgDto.getName();

        ImgBBDto imgBBDto = imagemService.uploadImage(image);
        if (imgBBDto == null) {
            return ResponseEntity.notFound().build();
        }

        String url = imgBBDto.getData().getUrl();

        if(characterService.getCharByIdGot(id).isPresent()) {
            Optional<CharacterEntity> characterExists = characterService.getCharByIdGot(id);
            characterService.deleteCharacter(characterExists.get());
        }

        CharacterEntity character = auxSave(name,url,id, characterDto);
        return ResponseEntity.ok(characterService.save(character));

    }



    public CharacterEntity auxSave(String name, String url, Long id, CharacterDto characterDto) {
        CharacterEntity characterEntity = new CharacterEntity();

        characterEntity.setName(name);
        characterEntity.setId_api_got(id);
        characterEntity.setUrl(url);
        characterEntity.setFirstName(characterDto.getFirstName());
        characterEntity.setLastName(characterDto.getLastName());
        characterEntity.setFullName(characterDto.getFullName());
        characterEntity.setTitle(characterDto.getTitle());
        characterEntity.setFamily(characterDto.getFamily());

        return characterEntity;
    }

}
