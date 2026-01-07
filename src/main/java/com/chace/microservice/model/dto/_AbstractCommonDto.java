package com.chace.microservice.model.dto;

import com.chace.microservice.model.entity._AbstractCommonModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

/* CHACE::OK */
@Getter @Setter @SuperBuilder @AllArgsConstructor @NoArgsConstructor
public class _AbstractCommonDto {

  //  private Long id; // id is handled inside each Dto, for the sake of renaming the id field to be more specific (ex: 'idEntityA' instead of 'id')
  private ZonedDateTime creationDate;
  private ZonedDateTime lastUpdateDate; // updateDate;
  private ZonedDateTime deleteDate;
  private Long          creatorCode;
  private Long          updaterCode;
  private Long          deleterCode;
  private Boolean       isDeleted;

  /* spare columns to be used for any emergency */
  private String tempData;
  private String extra;


  /**
   * This method helps fill the DTO common properties<br/>
   *
   * @param abstractEntity The (abstract) entity from which we want to retrieve common fields
   * @param abstractDto    The (abstract) DTO inside which we want to fill the common fields
   * @param <D>            The DTO type to be filled
   * @param <E>            The Entity Type providing common fields
   * @return The DTO type provided
   */
  public static <E extends _AbstractCommonModel, D extends _AbstractCommonDto> D setCommonFields(E abstractEntity, D abstractDto) {
    abstractDto.setCreationDate(abstractEntity.getCreationDate());
    abstractDto.setLastUpdateDate(abstractEntity.getUpdateDate());
    abstractDto.setDeleteDate(abstractEntity.getDeleteDate());
    abstractDto.setCreatorCode(abstractEntity.getCreatorCode());
    abstractDto.setUpdaterCode(abstractEntity.getUpdaterCode());
    abstractDto.setDeleterCode(abstractEntity.getDeleterCode());
    abstractDto.setIsDeleted(abstractEntity.getIsDeleted());

    abstractDto.setExtra(abstractEntity.getExtra());

    return abstractDto;
  }

}
