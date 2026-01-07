package com.chace.microservice.utilities;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class FATUtils {

    public static final String NOM_CARTE = "CARTE_";
    public static final int START_INDEX = 0;
    public static final int NB_ROWS = 20;


    public static Pageable ftaPagingSorting(Integer startIndex, Integer nbRows) {
        return PageRequest.of(Optional.ofNullable(startIndex).orElse(START_INDEX), Optional.ofNullable(nbRows).orElse(NB_ROWS), Sort.by("id").descending().and(Sort.by("creationDate").descending()).and(Sort.by("updateDate").descending()));
    }

    public static Pageable ftaPagingSorting(Integer startIndex, Integer nbRows, Sort sortingOptions) {
        return PageRequest.of(Optional.ofNullable(startIndex).orElse(START_INDEX), Optional.ofNullable(nbRows).orElse(NB_ROWS), sortingOptions);
    }

    public static String buildNomCarte(String codeCarte) {
        return NOM_CARTE.concat(codeCarte);
    }

}
