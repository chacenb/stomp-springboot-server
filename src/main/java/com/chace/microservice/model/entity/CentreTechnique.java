package com.chace.microservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* NORMALIZED OK */
@Entity(name = "centre_technique")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CentreTechnique extends _AbstractCommonModel {

    @Column(name = "codecentretechnique")
    private String codeCentreTechnique;

    @Column(name = "descriptioncentretechnique")
    private String descriptionCentreTechnique;

    @Column(name = "latcentretechnique")
    private Double latCentreTechnique;

    @Column(name = "longcentretechnique")
    private Double longCentreTechnique;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ville", foreignKey = @ForeignKey(name = "FK_CENTRE_GT_VILLE"))
    private Ville ville;

    @Transient
    private Long nbrePCO = 0L;

    @Transient
    private Long countRepartiteur = 0L;

}


