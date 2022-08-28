package dev.dracarys.gotdexreformed.imageService;

import dev.dracarys.gotdexreformed.dtos.ImgBBDto;
import reactor.core.publisher.Mono;

public interface ImagemService {


    ImgBBDto uploadImage(String image);

}
