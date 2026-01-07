package com.chace.microservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* NORMALIZED OK */
@Entity(name = "ville")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Ville extends _AbstractCommonModel {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_province", foreignKey = @ForeignKey(name = "FK_VILLE_PROVINCE"))
  private Province province;

  @Column(name = "codeville")
  private String codeVille;

  @Column(name = "nomville")
  private String nomVille;

  @Column(name = "latville")
  private Double latVille;

  @Column(name = "longville")
  private Double longVille;

}
