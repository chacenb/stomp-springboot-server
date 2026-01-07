package com.chace.microservice.model.dto;

import com.chace.microservice.exceptions.CustomException;
import com.chace.microservice.model.entity.CentreTechnique;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import static com.chace.microservice.exceptions.EExceptionCode.DTO_CONVERSION_ERROR;

/* NORMALIZED OK */
@Slf4j @Getter @Setter @SuperBuilder @AllArgsConstructor @NoArgsConstructor @ToString
public class CentreTechniqueDto extends _AbstractCommonDto {

    private Long     idCentreTechnique;
    @NotBlank(message = "codeCentreTechnique can not be empty or null")
    private String   codeCentreTechnique;
    private String   descriptionCentreTechnique;
    private Double   latCentreTechnique;
    private Double   longCentreTechnique;
    private VilleDto ville;
    private Long     nbrePCO             = 0L;
    private Long     countRepartiteur    = 0L;
    private Double   tauxGeolocalisation = 0D;

    @NotNull(message = "ville can not be null")
    private Long idVille;

    public static CentreTechniqueDto toDto(CentreTechnique entity) {
        try {
            return (entity == null) ? null : setCommonFields(
                    entity, CentreTechniqueDto.builder()
                                              .idCentreTechnique(entity.getId())
                                              .codeCentreTechnique(entity.getCodeCentreTechnique())
                                              .descriptionCentreTechnique(entity.getDescriptionCentreTechnique())
                                              .latCentreTechnique(entity.getLatCentreTechnique())
                                              .longCentreTechnique(entity.getLongCentreTechnique())
                                              .ville(VilleDto.toDto(entity.getVille()))
                                              .nbrePCO(entity.getNbrePCO())
                                              .countRepartiteur(entity.getCountRepartiteur())
                                              .build());
        } catch (Exception e) {
            throw new CustomException(DTO_CONVERSION_ERROR, e.getMessage());
        }
    }

    public static CentreTechnique toEntity(CentreTechniqueDto dto) {
        if (dto == null) return null;

        CentreTechnique entity = new CentreTechnique();
        entity.setId(dto.getIdCentreTechnique());
        entity.setCodeCentreTechnique(dto.getCodeCentreTechnique());
        entity.setDescriptionCentreTechnique(dto.getDescriptionCentreTechnique());
        entity.setLatCentreTechnique(dto.getLatCentreTechnique());
        entity.setLongCentreTechnique(dto.getLongCentreTechnique());
        entity.setVille(VilleDto.toEntity((VilleDto) dto.getVille()));

        return entity;
    }

}
