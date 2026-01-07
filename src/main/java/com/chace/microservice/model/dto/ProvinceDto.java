package com.chace.microservice.model.dto;

import com.chace.microservice.exceptions.CustomException;
import com.chace.microservice.model.entity.Province;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import static com.chace.microservice.exceptions.EExceptionCode.DTO_CONVERSION_ERROR;

/* NORMALIZED OK */
@Slf4j @Getter @Setter @ToString @SuperBuilder @AllArgsConstructor @NoArgsConstructor
public class ProvinceDto extends _AbstractCommonDto {

  private Long   idProvince;
  private String codeProvince;
  private String descriptionProvince;
  private Double latProvince;
  private Double longProvince;


  public static ProvinceDto toDto(Province entity) {
    try {
      return (entity == null) ? null : setCommonFields(entity, ProvinceDto.builder()
                                                                          .idProvince(entity.getId())
                                                                          .codeProvince(entity.getCodeProvince())
                                                                          .descriptionProvince(entity.getDescriptionProvince())
                                                                          .latProvince(entity.getLatProvince())
                                                                          .longProvince(entity.getLongProvince())
                                                                          .build());
    } catch (Exception e) {
      throw new CustomException(DTO_CONVERSION_ERROR, e.getMessage());
    }
  }

  public static void fillEntityFromDto(ProvinceDto dto, Province entity) {
    entity.setCodeProvince(dto.getCodeProvince());
    entity.setDescriptionProvince(dto.getDescriptionProvince());
    entity.setLatProvince(dto.getLatProvince());
    entity.setLongProvince(dto.getLongProvince());
  }


  public static Province toEntity(ProvinceDto dto) {
    if (dto == null) return null;

    Province entity = new Province();
    entity.setId(dto.getIdProvince());
    fillEntityFromDto(dto, entity);
    return entity;
  }

}
