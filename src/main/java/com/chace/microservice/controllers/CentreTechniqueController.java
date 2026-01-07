package com.chace.microservice.controllers;

import com.chace.microservice.model.dto.CentreTechniqueDto;
import com.chace.microservice.model.entity.CentreTechnique;
import com.chace.microservice.model.utils.FtaResponse;
import com.chace.microservice.services.CentreTechniqueService;
import com.chace.microservice.controllers.ifaces.ICentreTechniqueController;
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
public class CentreTechniqueController implements ICentreTechniqueController {

    private final CentreTechniqueService centreTechniqueService;


    @Override
    public FtaResponse<List<CentreTechniqueDto>> findAll(String title, Boolean deleted, Integer startIndex, Integer nbRows) {
        Page<CentreTechnique> items = centreTechniqueService.findAll(title, deleted, startIndex, nbRows);
        return FtaResponse.<List<CentreTechniqueDto>>builder()
                .timeStamp(ZonedDateTime.now())
                .status(HttpStatus.OK)
                .message("content inside data")
                .data(items.get().map(CentreTechniqueDto::toDto).toList())
                .total((int) items.getTotalElements())
                .page(items.getTotalPages())
                .build();
    }

    @Override
    public FtaResponse<CentreTechniqueDto> getOne(Long idCT, Boolean deleted) {
        return FtaResponse.<CentreTechniqueDto>builder()
                .timeStamp(ZonedDateTime.now())
                .status(HttpStatus.OK)
                .message("content inside data")
                .data(CentreTechniqueDto.toDto(centreTechniqueService.getOne(idCT, deleted)))
                .build();
    }

    @Override
    public FtaResponse<CentreTechniqueDto> save(CentreTechniqueDto centreTechDto) {
        return FtaResponse.<CentreTechniqueDto>builder()
                .timeStamp(ZonedDateTime.now())
                .status(HttpStatus.OK)
                .message("content inside data")
                .data(CentreTechniqueDto.toDto(centreTechniqueService.saveOrUpdateCentreTechnique(centreTechDto)))
                .build();

    }

    @Override
    public FtaResponse<CentreTechniqueDto> update(CentreTechniqueDto centreTechDto, Long idCT) {
        centreTechDto.setIdCentreTechnique(idCT);
        return FtaResponse.<CentreTechniqueDto>builder()
                .timeStamp(ZonedDateTime.now())
                .status(HttpStatus.OK)
                .message("content inside data")
                .data(CentreTechniqueDto.toDto(centreTechniqueService.saveOrUpdateCentreTechnique(centreTechDto)))
                .build();

    }

    @Override
    public FtaResponse<Boolean> delete(Long idCT) {
        return FtaResponse.<Boolean>builder()
                .timeStamp(ZonedDateTime.now())
                .status(HttpStatus.OK)
                .message("content inside data")
                .data((Objects.nonNull(centreTechniqueService.delete(idCT))))
                .build();
    }

//    @Override
//    public FtaResponse<List<MapVisualizationProjectionDto>> findAllForMapVisualization(String title, Boolean deleted, Integer startIndex, Integer nbRows) {
//        List<MapVisualizationProjectionDto> items = centreTechniqueService.findAllForMapVisualization(title, deleted, startIndex, nbRows);
//        return FtaResponse.<List<MapVisualizationProjectionDto>>builder()
//                .timeStamp(ZonedDateTime.now())
//                .status(HttpStatus.OK)
//                .message("content inside data")
//                .data(items)
//                .total(items.size())
//                .page(1)
//                .build();
//    }


//    public FtaResponse<List<MapVisualizationProjectionDto>> findNearestCentreTechniquesForMap(double lat, double lon, int limit, Boolean deleted, Integer startIndex, Integer nbRows) {
//        List<MapVisualizationProjectionDto> result = centreTechniqueService.findNearestCentreTechniquesForMap(lat, lon, limit, deleted, startIndex, nbRows);
//        return FtaResponse.<List<MapVisualizationProjectionDto>>builder()
//                          .timeStamp(ZonedDateTime.now())
//                          .status(HttpStatus.OK)
//                          .message("content inside data")
//                          .data(result)
//                          .total(result.size())
//                          .page(1)
//                          .build();
//    }


//    @Override
//    public FtaResponse<List<MapVisualizationProjectionDto>> findNearestEntities(Long centreTechnique, Double lat, Double lon, Boolean deleted, Integer startIndex, Integer nbRows) {
//        var result = centreTechniqueService.findNearestEntities(centreTechnique, lat, lon, nbRows);
//        return FtaResponse.<List<MapVisualizationProjectionDto>>builder()
//                .timeStamp(ZonedDateTime.now())
//                .status(HttpStatus.OK)
//                .message("content inside data")
//                .data(result)
//                .total(result.size() - 1)
//                .page(1)
//                .build();
//    }


}
