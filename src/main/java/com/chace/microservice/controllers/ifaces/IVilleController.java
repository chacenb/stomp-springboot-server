package com.chace.microservice.controllers.ifaces;

import com.chace.microservice.model.dto.VilleDto;
import com.chace.microservice.model.utils.FtaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Ville")
@RequestMapping(path = "/ville")
public interface IVilleController {

  @GetMapping
  @Operation(summary = "obtenir la liste des villes par page ")
  FtaResponse<List<VilleDto>> findAll(@RequestParam(name = "provinces", required = false) List<Long> provinces,
                                      // -------- default request parameters ---------------------
                                      @RequestParam(name = "title", required = false) String title,
                                      @RequestParam(name = "deleted", required = false, defaultValue = "false") Boolean deleted,
                                      @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
                                      @RequestParam(name = "nbRows", required = false, defaultValue = "10") Integer nbRows);

  @GetMapping("{idVille}")
  @Operation(summary = "récuperer ville en fournissant son ID ")
  FtaResponse<VilleDto> getOne(@PathVariable Long idVille,
                               @RequestParam(name = "deleted", required = false, defaultValue = "false") Boolean deleted);

  @PostMapping
  @Operation(summary = "ajouter une nouvelle ville ")
  FtaResponse<VilleDto> save(@RequestBody @Valid VilleDto villeDto);

  @PutMapping("{idVille}")
  @Operation(summary = "faire la mise à jour des informations d'une ville en fournissant son ID ")
  FtaResponse<VilleDto> update(@RequestBody @Valid VilleDto villeDto, @PathVariable Long idVille);

  @DeleteMapping("{idVille}")
  @Operation(summary = "supprimer une ville en fournissant son ID ")
  FtaResponse<Boolean> delete(@PathVariable Long idVille);
}
