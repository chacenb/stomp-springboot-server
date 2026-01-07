package com.chace.microservice.controllers;

import com.chace.microservice.model.dto.VilleDto;
import com.chace.microservice.model.entity.Ville;
import com.chace.microservice.model.utils.FtaResponse;
import com.chace.microservice.services.VilleService;
import com.chace.microservice.controllers.ifaces.IVilleController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j @RestController @RequiredArgsConstructor @CrossOrigin("*")
public class VilleController implements IVilleController {

    private final VilleService villeService;


    @Override
    public FtaResponse<List<VilleDto>> findAll(List<Long> provinces, String title, Boolean deleted, Integer startIndex, Integer nbRows) {
        Page<Ville> items = villeService.findAll(provinces, title, deleted, startIndex, nbRows);
        log.info("items = {}", items);
        return FtaResponse.<List<VilleDto>>builder()
                .timeStamp(ZonedDateTime.now())
                .status(HttpStatus.OK)
                .message("content inside data")
                .data(items.get().map(VilleDto::toDto).toList())
                .total((int) items.getTotalElements())
                .page(items.getTotalPages())
                .build();
    }


    @Override
    public FtaResponse<VilleDto> getOne(Long idVille, Boolean deleted) {
        return FtaResponse.<VilleDto>builder()
                .timeStamp(ZonedDateTime.now())
                .status(HttpStatus.OK)
                .message("content inside data")
                .data(VilleDto.toDto(villeService.getOne(idVille, deleted)))
                .build();
    }

    @Override
    public FtaResponse<VilleDto> save(VilleDto villeDto) {
        return FtaResponse.<VilleDto>builder()
                .timeStamp(ZonedDateTime.now())
                .status(HttpStatus.OK)
                .message("content inside data")
                .data(VilleDto.toDto(villeService.saveOrUpdateVille(villeDto)))
                .build();
    }

    @Override
    public FtaResponse<VilleDto> update(VilleDto villeDto, Long idVille) {
        villeDto.setIdVille(idVille);
        return FtaResponse.<VilleDto>builder()
                .timeStamp(ZonedDateTime.now())
                .status(HttpStatus.OK)
                .message("content inside data")
                .data(VilleDto.toDto(villeService.saveOrUpdateVille(villeDto)))
                .build();
    }

    @Override
    public FtaResponse<Boolean> delete(Long idVille) {
        return FtaResponse.<Boolean>builder()
                .timeStamp(ZonedDateTime.now())
                .status(HttpStatus.OK)
                .message("content inside data")
                .data((Objects.nonNull(villeService.delete(idVille))))
                .build();
    }
}
