package com.chace.microservice.repositories;

import com.chace.microservice.model.entity.Province;
import com.chace.microservice.model.entity.Province_;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProvinceRepository extends JpaRepository<Province, Long> {

  Page<Province> findAll(Specification<Province> specification, Pageable pageable);

  Optional<Province> findByIdAndIsDeleted(Long id, Boolean isDeleted);

  Optional<Province> findByCodeProvinceAndIsDeleted(String codeProvince, boolean b);

  Optional<Province> findOne(Specification<Province> specification);



  /* ---------------------------------------------------------------------------- */
  /* define all the specifications here to construct queries with criterias  */

  static Specification<Province> isDeleted(Boolean trueFalse) {
    return (T, cq, cb) -> cb.equal(T.get(Province_.isDeleted), trueFalse);
  }

  static Specification<Province> contains(String searchString) {
    return (T, cq, cb) -> cb.or(cb.like(T.get(Province_.CODE_PROVINCE), "%" + searchString + "%"), cb.like(T.get(Province_.DESCRIPTION_PROVINCE), "%" + searchString + "%"));
  }


  static Specification<Province> differsFrom(Long value) {
    return (T, cq, cb) -> cb.notEqual(T.get(Province_.ID), value);
  }

  static Specification<Province> codeProvinceOrDescProvinceLike(String searchString) {
    return (T, cq, cb) -> cb.or(
        cb.like(T.get(Province_.CODE_PROVINCE), "%" + searchString + "%"),
        cb.like(T.get(Province_.DESCRIPTION_PROVINCE), "%" + searchString + "%")
                               );
  }

  static Specification<Province> ofCode(String searchString) {
    return (T, cq, cb) -> cb.equal(cb.lower(T.get(Province_.CODE_PROVINCE)), searchString);
  }

}
