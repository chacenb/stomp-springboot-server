package com.chace.microservice.repositories;

import com.chace.microservice.model.entity.CentreTechnique;
import com.chace.microservice.model.entity.CentreTechnique_;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CentreTechniqueRepository extends JpaRepository<CentreTechnique, Long> {

    Page<CentreTechnique> findAll(Specification<CentreTechnique> specification, Pageable pageable);

    Optional<CentreTechnique> findByIdAndIsDeleted(Long id, Boolean isDeleted);

    @Query("SELECT v FROM centre_technique v WHERE v.isDeleted=FALSE")
    Page<CentreTechnique> findAll(Pageable pageable);

    List<CentreTechnique> findAllByIsDeleted(Boolean isDeleted);


    interface CTProjectionMap {
        Long getId();

        Double getLat();

        Double getLng();

        String getTitle();

        String getDescription();

        Double getCountRepartiteur();

        Double getDistance();
    }

    public record TopCentreActiveLinesDto(Long idCentre,
                                          String libelleCentre,
                                          Long nbLignesActives) {
    }


    /* Interface-based projection JPA */
    @Query(value = """
              SELECT 
              ct.id, 
              ct.latcentretechnique AS lat, 
              ct.longcentretechnique AS lng, 
              ct.codecentretechnique AS title, 
              ct.descriptioncentretechnique AS description, 
              COUNT(r.id) AS countRepartiteur 
            FROM newfta.centre_technique ct 
            LEFT JOIN newfta.repartiteur r 
              ON r.id_centre_technique = ct.id 
              AND r.boo_suppr = FALSE 
            WHERE ct.boo_suppr = FALSE 
              AND ct.latcentretechnique IS NOT NULL 
              AND ct.longcentretechnique IS NOT NULL 
            GROUP BY ct.id, ct.latcentretechnique, ct.longcentretechnique, ct.codecentretechnique, ct.descriptioncentretechnique 
            ORDER BY ct.id ASC 
            """, nativeQuery = true)
    List<CTProjectionMap> findAllProjectedLiteInterfaceBased();

//    @Query(value = """
//    SELECT
//        ct.id, ct.code_centre_technique, ct.description_centre_technique, ct.lat_centre_technique, ct.long_centre_technique,
//        ct.id_ville, ct.hor_creat, ct.hor_maj, ct.hor_suppr,
//        ct.id_util_creat, ct.id_util_maj, ct.id_util_suppr, ct.boo_suppr, ct.temp_data, ct.extra
//    FROM newfta.centre_technique ct
//    JOIN newfta.ville v ON ct.id_ville = v.id
//    WHERE ct.boo_suppr = FALSE
//      AND ct.lat_centre_technique IS NOT NULL
//      AND ct.long_centre_technique IS NOT NULL
//      AND v.codeville = :codeVille
//    ORDER BY (
//        6371 * acos(
//            cos(radians(:lat)) * cos(radians(ct.lat_centre_technique)) *
//            cos(radians(ct.long_centre_technique) - radians(:lon)) +
//            sin(radians(:lat)) * sin(radians(ct.lat_centre_technique))
//        )
//    ) ASC
//    LIMIT :limit
//""", nativeQuery = true)
//    List<CentreTechnique> findNearestCentresTechniques(@Param("lat") double lat,
//                                                       @Param("lon") double lon,
//                                                       @Param("codeVille") String codeVille,
//                                                       @Param("limit") int limit);
    @Query(value = """
            SELECT
                    ct.id AS id,
                    ct.latcentretechnique AS lat,
                    ct.longcentretechnique AS lng,
                    ct.codecentretechnique AS title,
                    ct.descriptioncentretechnique AS description,
                    COUNT(r.id) AS countRepartiteur,
                    6371 * acos(
                        cos(radians(:lat)) * cos(radians(ct.latcentretechnique)) *
                        cos(radians(ct.longcentretechnique) - radians(:lon)) +
                        sin(radians(:lat)) * sin(radians(ct.latcentretechnique))
                    ) AS distance
                FROM newfta.centre_technique ct 
                LEFT JOIN newfta.repartiteur r 
                  ON r.id_centre_technique = ct.id 
                  AND r.boo_suppr = FALSE 
                WHERE ct.boo_suppr = FALSE 
                  AND ct.latcentretechnique IS NOT NULL 
                  AND ct.longcentretechnique IS NOT NULL 
                GROUP BY ct.id, ct.latcentretechnique, ct.longcentretechnique, ct.codecentretechnique, ct.descriptioncentretechnique 
                ORDER BY distance ASC
                LIMIT :limit
                """, nativeQuery = true)
    List<CTProjectionMap> findNearestCTFromPosition(@Param("lat") Double lat,
                                                    @Param("lon") Double lon,
                                                    @Param("limit") int limit);





    /* ---------------------------------------------------------------------------- */
    /* define all the specifications here to construct queries with criterias  */

    static Specification<CentreTechnique> isDeleted(Boolean trueFalse) {
        return (T, cq, cb) -> cb.equal(T.get(CentreTechnique_.isDeleted), trueFalse);
    }

    static Specification<CentreTechnique> ofId(List<Long> list) {
        return (T, cq, cb) -> T.get(CentreTechnique_.id).in(list); /* where repartiteur.type.id in [1,2,...] */
    }

    static Specification<CentreTechnique> contains(String searchString) {
        return (T, cq, cb) -> cb.like(T.get(CentreTechnique_.CODE_CENTRE_TECHNIQUE), "%" + searchString + "%");
    }

}
