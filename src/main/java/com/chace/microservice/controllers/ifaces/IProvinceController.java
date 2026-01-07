package com.chace.microservice.controllers.ifaces;

import com.chace.microservice.model.dto.ProvinceDto;
import com.chace.microservice.model.utils.FtaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Province")
@RequestMapping(path = "/province")
public interface IProvinceController {

  @GetMapping
  @Operation(summary = "obtenir la liste des provinces par page ")
  FtaResponse<List<ProvinceDto>> findAll(// -------- default request parameters ---------------------
                                         @RequestParam(name = "title", required = false) String title,
                                         @RequestParam(name = "deleted", required = false, defaultValue = "false") Boolean deleted,
                                         @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
                                         @RequestParam(name = "nbRows", required = false, defaultValue = "10") Integer nbRows);

  @GetMapping("{idProvince}")
  @Operation(summary = "récuperer province en fournissant son ID ")
  FtaResponse<ProvinceDto> getOne(@PathVariable Long idProvince,
                                  @RequestParam(name = "deleted", required = false, defaultValue = "false") Boolean deleted);

  @PostMapping
  @Operation(summary = "ajouter une nouvelle province ")
  FtaResponse<ProvinceDto> save(@RequestBody @Valid ProvinceDto provinceDto);

  @PutMapping("{idProvince}")
  @Operation(summary = "faire la mise à jour des informations d'une province en fournissant son ID ")
  FtaResponse<ProvinceDto> update(@RequestBody @Valid ProvinceDto provinceDto, @PathVariable Long idProvince);

  @DeleteMapping("{idProvince}")
  @Operation(summary = "supprimer une province en fournissant son ID ")
  FtaResponse<Boolean> delete(@PathVariable Long idProvince);
}
