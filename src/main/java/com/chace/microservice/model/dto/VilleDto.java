package com.chace.microservice.model.dto;


import com.chace.microservice.exceptions.CustomException;
import com.chace.microservice.model.entity.Ville;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import static com.chace.microservice.exceptions.EExceptionCode.DTO_CONVERSION_ERROR;

/* NORMALIZED OK */
@Slf4j @Getter @Setter @SuperBuilder @AllArgsConstructor @NoArgsConstructor
public class VilleDto extends _AbstractCommonDto {

  private Long        idVille;
  private ProvinceDto province;
  private String      codeVille;
  private String      nomVille;
  private Double      latVille;
  private Double      longVille;

  private Long idProvince;

  public static VilleDto toDto(Ville entity) {
    try {
      return (entity == null) ? null : setCommonFields(entity, VilleDto.builder()
                                                                       .idVille(entity.getId())
                                                                       .province(ProvinceDto.toDto(entity.getProvince()))
                                                                       .codeVille(entity.getCodeVille())
                                                                       .nomVille(entity.getNomVille())
                                                                       .latVille(entity.getLatVille())
                                                                       .longVille(entity.getLongVille())
                                                                       .build());
    } catch (Exception e) {
      throw new CustomException(DTO_CONVERSION_ERROR, e.getMessage());
    }
  }

  public static void fillSimpleFieldsFromDto(VilleDto dto, Ville entity) {
    entity.setCodeVille(dto.getCodeVille());
    entity.setNomVille(dto.getNomVille());
    entity.setLatVille(dto.getLatVille());
    entity.setLongVille(dto.getLongVille());
  }

  public static Ville toEntity(VilleDto dto) {
    if (dto == null) return null;

    Ville entity = new Ville();
    entity.setId(dto.getIdVille());
    fillSimpleFieldsFromDto(dto, entity);
    entity.setProvince(ProvinceDto.toEntity(dto.getProvince()));
    return entity;
  }


}
