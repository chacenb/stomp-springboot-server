package com.chace.microservice.services;


import com.chace.microservice.exceptions.CustomException;
import com.chace.microservice.exceptions.EExceptionCode;
import com.chace.microservice.model.dto.CentreTechniqueDto;
import com.chace.microservice.model.entity.CentreTechnique;
import com.chace.microservice.model.entity.Ville;
import com.chace.microservice.repositories.CentreTechniqueRepository;
import com.chace.microservice.repositories.VilleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

import static com.chace.microservice.repositories.CentreTechniqueRepository.contains;
import static com.chace.microservice.repositories.CentreTechniqueRepository.isDeleted;
import static com.chace.microservice.utilities.FATUtils.*;

@Slf4j @Service @Transactional @RequiredArgsConstructor
public class CentreTechniqueService {

    private final CentreTechniqueRepository centreTechniqueRepo;
    private final VilleRepository villeRepository;
    private final SimpMessagingTemplate messagingTemplate;

//    private final RepartiteurRepository repartiteurRepository;


    public Page<CentreTechnique> findAll(String title, Boolean deleted, Integer startIndex, Integer nbRows) {
        log.info("finding All centres techniques startIndex = {}, nbRows = {}, deleted = {}, title = {}", startIndex, nbRows, deleted, title);

        /* build a specification for filtering purpose and add by default isNotDeleted >> then add other filterCriterias  */
        Specification<CentreTechnique> filterCriterias = isDeleted(deleted);
        if (Objects.nonNull(title)) filterCriterias = filterCriterias.and(contains(title));

        /* get and return items from DB */
        return centreTechniqueRepo.findAll(filterCriterias, ftaPagingSorting(startIndex, nbRows, Sort.by("descriptionCentreTechnique").ascending()));
    }

    public CentreTechnique getOne(Long idCT, Boolean deleted) {
        return centreTechniqueRepo.findByIdAndIsDeleted(idCT, deleted).orElseThrow(() -> new CustomException(EExceptionCode.WRONG_DELETED_ENTITY, "Centre technique of id " + idCT + " doesn't exist / is deleted"));
    }

    public CentreTechnique saveOrUpdateCentreTechnique(CentreTechniqueDto inputDto) {
        log.info(":: inputDto = {}", inputDto);

        /* get the entity if it exists, else create brand-new Obj */
        CentreTechnique centreTechnique = Optional.ofNullable(inputDto.getIdCentreTechnique()).map(centreTechniqueRepo::getReferenceById).orElse(new CentreTechnique());

        /* Make sure that : centreTechnique, ville && theTypeRepart exist, else throw CustomException exception */
        Ville fetchedVille = villeRepository.findById(inputDto.getIdVille()).orElseThrow(() -> new CustomException(EExceptionCode.WRONG_DELETED_ENTITY, "Ville of id " + inputDto.getIdVille() + " doesn't exist / is deleted"));

        /* fill the entity to save */
        centreTechnique.setCodeCentreTechnique(inputDto.getCodeCentreTechnique());
        centreTechnique.setDescriptionCentreTechnique(inputDto.getDescriptionCentreTechnique());
        centreTechnique.setLatCentreTechnique(inputDto.getLatCentreTechnique());
        centreTechnique.setLongCentreTechnique(inputDto.getLongCentreTechnique());
        centreTechnique.setVille(fetchedVille);

        /* save and return the saved value */
        return centreTechniqueRepo.save(centreTechnique);
    }

    public CentreTechnique delete(Long idCT) {
        log.info("delete CentreTechnique of id = {}", idCT);

        /* Check if the entity exist */
        CentreTechnique toDelete = centreTechniqueRepo.findByIdAndIsDeleted(idCT, false).orElseThrow(() -> new CustomException(EExceptionCode.WRONG_DELETED_ENTITY, "Centre technique of id " + idCT + " doesn't exist / is deleted"));

        /* Get the currently authenticated user from Spring security's ContextHolder */
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        /* Set the deleter code, mark as deleted && set the delete date */
        // toDelete.setDeleterCode(principal.getUserId());
        toDelete.setDeleteDate(ZonedDateTime.now());
        toDelete.setDeleted(true);

        log.info("!!! ENTITY {} [{}] DELETED SUCCESSFULLY, is it deleted? = {} ", toDelete.getCodeCentreTechnique(), toDelete.getId(), toDelete.isDeleted());

        return centreTechniqueRepo.save(toDelete);
    }

    @Scheduled(fixedRate = 1000)
    public void getPeriodicCentreTechniqueInfos() {
        String message = "Msg::" + System.currentTimeMillis();
        messagingTemplate.convertAndSend(SOCKET_OUTPUT_PREFIX + SOCKET_OUTPUT_MESSAGE_TOPIC, message);
        log.info("____ Broadcasted message : " + message);
    }

}
