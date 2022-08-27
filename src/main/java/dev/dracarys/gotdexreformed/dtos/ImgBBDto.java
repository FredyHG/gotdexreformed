package dev.dracarys.gotdexreformed.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.io.Serializable;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Data
public class ImgBBDto implements Serializable {
    private ImgBBDataDto data;
}
