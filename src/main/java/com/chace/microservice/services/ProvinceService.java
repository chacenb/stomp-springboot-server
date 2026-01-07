package com.chace.microservice.services;


import com.chace.microservice.exceptions.CustomException;
import com.chace.microservice.exceptions.EExceptionCode;
import com.chace.microservice.model.dto.ProvinceDto;
import com.chace.microservice.model.entity.Province;
import com.chace.microservice.repositories.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Objects;

import static com.chace.microservice.repositories.ProvinceRepository.*;
import static com.chace.microservice.utilities.FATUtils.ftaPagingSorting;

@Slf4j @Service @RequiredArgsConstructor @Transactional
public class ProvinceService {

  private final ProvinceRepository provinceRepository;

  public Page<Province> findAll(String title, Boolean deleted, Integer startIndex, Integer nbRows) {
    log.info("finding All Provinces deleted = {}, startIndex = {}, nbRows = {}, title = {} ", deleted, startIndex, nbRows, title);

    /* build a specification for filtering purpose >> then add all filterCriterias  */
    Specification<Province> filterCriterias = isDeleted(deleted);
    if (Objects.nonNull(title)) filterCriterias = filterCriterias.and(contains(title));

    /* get and return items from DB */
    return provinceRepository.findAll(filterCriterias, ftaPagingSorting(startIndex, nbRows));
  }

  public Province getOne(Long idProvince, Boolean deleted) {
    return provinceRepository.findByIdAndIsDeleted(idProvince, deleted).orElseThrow(() -> new CustomException(EExceptionCode.WRONG_DELETED_ENTITY, "province of id " + idProvince + " doesn't exist / is deleted"));
  }

  public Province saveOrUpdateProvince(ProvinceDto inputDto) {
    log.info(":: inputDto = {}", inputDto);

    /* get the active entity if it exists, else create brand-new Obj */
    Province province = provinceRepository.findByIdAndIsDeleted(inputDto.getIdProvince(), false).orElse(new Province());

    /* Handle uniqueness/duplicates of CodeProvince */
    {
      /* if new creation, province codeProvince should be unique */
      if (Objects.isNull(inputDto.getIdProvince()))
        if (provinceRepository.findOne(ofCode(inputDto.getCodeProvince().toLowerCase()).and(isDeleted(false))).isPresent())
          throw new CustomException(EExceptionCode.DATA_INCOHERENCE, "Province of codeProvince [" + inputDto.getCodeProvince() + "] already exists");

      /* if update, province should be unique */
      if (Objects.nonNull(inputDto.getIdProvince()))
        if (provinceRepository.findOne(ofCode(inputDto.getCodeProvince().toLowerCase()).and(isDeleted(false)).and(differsFrom(province.getId()))).isPresent())
          throw new CustomException(EExceptionCode.DATA_INCOHERENCE, "Province of codeProvince [" + inputDto.getCodeProvince() + "] already exists");
    }

    /* Make sure that dependencies exist, else throw CustomException exception */
    /* NO DEPENDENCY */

    /* fill the entity to save */
    ProvinceDto.fillEntityFromDto(inputDto, province);

    /* return the saved value */
    return provinceRepository.save(province);
  }

  public Province delete(Long idProvince) {
    log.info("delete Province of id = {}", idProvince);

    /* Check if the entity exist */
    Province toDelete = provinceRepository.findByIdAndIsDeleted(idProvince, false).orElseThrow(() -> new CustomException(EExceptionCode.ENTITY_NOT_FOUND, "province of id " + idProvince + " doesn't exist / is deleted"));

    /* Get the currently authenticated user from Spring security's ContextHolder */
    // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

    /* Set the deleter code, mark as deleted && set the delete date */
    // toDelete.setDeleterCode(principal.getUserId());
    toDelete.setDeleteDate(ZonedDateTime.now());
    toDelete.setDeleted(true);

    log.info("!!! ENTITY {} [{}] DELETED SUCCESSFULLY, is it deleted? = {} ", toDelete.getCodeProvince(), toDelete.getId(), toDelete.isDeleted());

    return provinceRepository.save(toDelete);
  }
}
