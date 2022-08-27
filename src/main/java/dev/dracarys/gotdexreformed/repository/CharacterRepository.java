package dev.dracarys.gotdexreformed.repository;

import dev.dracarys.gotdexreformed.models.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {
    @Query(value ="select * FROM persons_table persons WHERE persons.id_api_got = :id", nativeQuery = true)
    public Optional<CharacterEntity> findByIdGot(Long id);
    @Query(value ="select * FROM persons_table persons WHERE url IS NOT NULL", nativeQuery = true)
    public List<CharacterEntity> findAllCharactersWithImage();
}
