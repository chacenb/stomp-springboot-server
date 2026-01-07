package com.chace.microservice.controllers.ifaces;

import com.chace.microservice.model.dto.CentreTechniqueDto;
import com.chace.microservice.model.utils.FtaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Centre Technique")
@RequestMapping(path = "/centre-technique")
public interface ICentreTechniqueController {

    @GetMapping
    @Operation(summary = "obtenir la liste des centres technique par page ")
    public FtaResponse<List<CentreTechniqueDto>> findAll(// -------- default request parameters ---------------------
                                                         @RequestParam(name = "title", required = false) String title,
                                                         @RequestParam(name = "deleted", required = false, defaultValue = "false") Boolean deleted,
                                                         @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
                                                         @RequestParam(name = "nbRows", required = false, defaultValue = "10") Integer nbRows);

    @GetMapping("{idCT}")
    @Operation(summary = "récuperer centre technique en fournissant son ID ")
    public FtaResponse<CentreTechniqueDto> getOne(@PathVariable Long idCT,
                                                  @RequestParam(name = "deleted", required = false, defaultValue = "false") Boolean deleted);


    @PostMapping
    @Operation(summary = "ajouter un nouveau centre technique ")
    public FtaResponse<CentreTechniqueDto> save(@RequestBody @Valid CentreTechniqueDto centreTechDto);

    @PutMapping("{idCT}")
    @Operation(summary = "faire la mise à jour des informations d'un centre technique en fournissant son ID ")
    public FtaResponse<CentreTechniqueDto> update(@RequestBody @Valid CentreTechniqueDto centreTechDto, @PathVariable Long idCT);

    @DeleteMapping("{idCT}")
    @Operation(summary = "supprimer un centre technique en fournissant son ID ")
    public FtaResponse<Boolean> delete(@PathVariable Long idCT);

//    @GetMapping("find-all-map-visualization")
//    @Operation(summary = "")
//    FtaResponse<List<MapVisualizationProjectionDto>> findAllForMapVisualization(// -------- default request parameters ---------------------
//                                                                                @RequestParam(name = "title", required = false) String title,
//                                                                                @RequestParam(name = "deleted", required = false, defaultValue = "false") Boolean deleted,
//                                                                                @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
//                                                                                @RequestParam(name = "nbRows", required = false, defaultValue = "10") Integer nbRows);
//
//    @GetMapping("find-all-nearest")
//    public FtaResponse<List<MapVisualizationProjectionDto>> findNearestEntities(
//            @RequestParam(name = "centreTechnique", required = false) Long centreTechnique,
//            @RequestParam(name = "lat") Double lat,
//            @RequestParam(name = "lon") Double lon,
//            // -------- default request parameters ---------------------
//            @RequestParam(name = "deleted", required = false, defaultValue = "false") Boolean deleted,
//            @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
//            @RequestParam(name = "nbRows", required = false, defaultValue = "10") Integer nbRows);

}

