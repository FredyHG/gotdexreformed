package dev.dracarys.gotdexreformed.services;

import dev.dracarys.gotdexreformed.models.CharacterEntity;
import dev.dracarys.gotdexreformed.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    public Optional<CharacterEntity> getCharByIdGot(Long id) {
        return characterRepository.findByIdGot(id);
    }

    @Transactional
    public CharacterEntity save(CharacterEntity characterEntity) {
        return characterRepository.save(characterEntity);
    }

    public String deleteCharacter(CharacterEntity characterEntity) {
        if(getCharByIdGot(characterEntity.getId_api_got()).isEmpty()) {
            return "O personagem nao existe!";
        }
        characterRepository.delete(characterEntity);
        return "Person delete successfully";
    }
}
