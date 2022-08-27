package dev.dracarys.gotdexreformed.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CharacterDto {
    @Setter
    @Getter
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String title;
    private String family;
    @Setter
    @Getter
    private String url;
}
