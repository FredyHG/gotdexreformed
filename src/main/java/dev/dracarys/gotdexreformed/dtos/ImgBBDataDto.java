package dev.dracarys.gotdexreformed.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ImgBBDataDto implements Serializable {
    private String url;

    @JsonProperty("link")
    public void setLink(String url){
        this.url = url;
    }
}
