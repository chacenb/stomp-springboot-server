package com.chace.microservice.repositories;

import com.chace.microservice.model.entity.Province_;
import com.chace.microservice.model.entity.Ville;
import com.chace.microservice.model.entity.Ville_;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VilleRepository extends JpaRepository<Ville, Long> {

    Page<Ville> findAll(Specification<Ville> specification, Pageable pageable);

    Optional<Ville> findByIdAndIsDeleted(Long id, Boolean isDeleted);

    /* ---------------------------------------------------------------------------- */
    /* define all the specifications here to construct queries with criterias  */

    static Specification<Ville> isDeleted(Boolean trueFalse) {
        return (T, cq, cb) -> cb.equal(T.get(Ville_.isDeleted), trueFalse);
    }

    static Specification<Ville> contains(String searchString) {
        return (T, cq, cb) -> cb.or(cb.like(T.get(Ville_.nomVille), "%" + searchString + "%"), cb.like(T.get(Ville_.codeVille), "%" + searchString + "%"));
    }

    static Specification<Ville> ofProvinces(List<Long> provinces) {
        return (T, cq, cb) -> T.get(Ville_.province).get(Province_.id).in(provinces);
    }

}
