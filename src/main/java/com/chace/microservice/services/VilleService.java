package com.chace.microservice.services;


import com.chace.microservice.exceptions.CustomException;
import com.chace.microservice.exceptions.EExceptionCode;
import com.chace.microservice.model.dto.VilleDto;
import com.chace.microservice.model.entity.Province;
import com.chace.microservice.model.entity.Ville;
import com.chace.microservice.repositories.ProvinceRepository;
import com.chace.microservice.repositories.VilleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

import static com.chace.microservice.repositories.VilleRepository.isDeleted;
import static com.chace.microservice.repositories.VilleRepository.ofProvinces;
import static com.chace.microservice.utilities.FATUtils.ftaPagingSorting;

@Slf4j @Service @RequiredArgsConstructor @Transactional
public class VilleService {

  private final VilleRepository    villeRepository;
  private final ProvinceRepository provinceRepository;

  public Page<Ville> findAll(List<Long> provinces, String title, Boolean deleted, Integer startIndex, Integer nbRows) {
    log.info("finding All Ville, provinces = {}, startIndex = {}, nbRows = {}, deleted = {}, title = {}", provinces, startIndex, nbRows, deleted, title);

    /* build a specification for filtering purpose >> then add all filterCriterias  */
    Specification<Ville> filterCriterias = isDeleted(deleted);
    if (Objects.nonNull(provinces)) filterCriterias = filterCriterias.and(ofProvinces(provinces));

    /* get and return items from DB */
    return villeRepository.findAll(filterCriterias, ftaPagingSorting(startIndex, nbRows, Sort.by("nomVille").ascending()));
  }

  public Ville getOne(Long idVille, Boolean deleted) {
    return villeRepository.findByIdAndIsDeleted(idVille, deleted).orElseThrow(() -> new CustomException(EExceptionCode.WRONG_DELETED_ENTITY, "ville of id " + idVille + " doesn't exist / is deleted"));
  }

  public Ville saveOrUpdateVille(VilleDto inputDto) {
    log.info(":: inputDto = {}", inputDto);

    /* get the active entity if it exists, else create brand-new Obj */
    Ville ville = villeRepository.findByIdAndIsDeleted(inputDto.getIdVille(), false).orElse(new Ville());

    /* Make sure that dependencies exist, else throw CustomException exception */
    Province fetchedProvince = provinceRepository.findById(inputDto.getIdProvince()).orElseThrow(() -> new CustomException(EExceptionCode.WRONG_DELETED_ENTITY, "province of id " + inputDto.getIdProvince() + " doesn't exist"));

    /* fill the entity to save */
    VilleDto.fillSimpleFieldsFromDto(inputDto, ville);
    ville.setProvince(fetchedProvince);

    /* return the saved value */
    return villeRepository.save(ville);
  }

  public Ville delete(Long idVille) {
    log.info("delete Technicien of id = {}", idVille);

    /* Check if the entity exist */
    Ville toDelete = villeRepository.findByIdAndIsDeleted(idVille, false).orElseThrow(() -> new CustomException(EExceptionCode.ENTITY_NOT_FOUND, "ville of id " + idVille + " doesn't exist / is deleted"));

    /* Get the currently authenticated user from Spring security's ContextHolder */
    // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

    /* Set the deleter code, mark as deleted && set the delete date */
    // toDelete.setDeleterCode(principal.getUserId());
    toDelete.setDeleteDate(ZonedDateTime.now());
    toDelete.setDeleted(true);

    log.info("!!! ENTITY {} [{}] DELETED SUCCESSFULLY, is it deleted? = {} ", toDelete.getNomVille(), toDelete.getId(), toDelete.isDeleted());

    return villeRepository.save(toDelete);

  }
}
