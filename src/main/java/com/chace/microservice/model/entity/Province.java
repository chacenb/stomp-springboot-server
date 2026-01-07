package com.chace.microservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* NORMALIZED OK */
@Entity(name = "province")
/* Can't use uniqueConstraints cause items are not deleted in DB. this might block creations due to markedAsDeleted entities */
//@Table(uniqueConstraints = {
//    @UniqueConstraint(name = "UNIQUE_CODE_PROVINCE", columnNames = {"codeprovince"}),
//    @UniqueConstraint(name = "UNIQUE_DESC_PROVINCE", columnNames = {"descriptionprovince"})
//})
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Province extends _AbstractCommonModel {

  @Column(name = "codeprovince"/*, unique = true*/)
  private String codeProvince;

  @Column(name = "descriptionprovince"/*, unique = true*/)
  private String descriptionProvince;

  @Column(name = "latprovince")
  private Double latProvince;

  @Column(name = "longprovince")
  private Double longProvince;
}
