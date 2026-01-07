package com.chace.microservice.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


//import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZonedDateTime;

/* CHACE::OK */
@MappedSuperclass // Designates a class whose mapping information is applied to the entities that inherit from it. It has no separate table defined for it.
@Getter @Setter @ToString
public class _AbstractCommonModel {

    /* --------- I don't know what this is for  ---------- */
    private static String SYSTEM = "sytem";


    /* --------- Class properties ---------- */

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    // visit > https://www.baeldung.com/hibernate-date-time
    // Since Java 8, Java Date and Time API deals with temporal values fixing the problems of java.util.Date and java.util.Calendar.
    // java.time package types are directly mapped to corresponding SQL types, thus no need to explicitly specify @Temporal annotation:
    // LocalDate is mapped to DATE. LocalTime and OffsetTime are mapped to TIME. Instant, ZonedDateTime, OffsetDateTime and ZonedDateTime are mapped to TIMESTAMP.
    @Column(name = "hor_creat", length = 18, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime creationDate;

    @Column(name = "hor_maj", length = 18)
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime updateDate;

    @Column(name = "hor_suppr", length = 18)
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime deleteDate;

    @Column(name = "id_util_creat", length = 18)
    private Long creatorCode;

    @Column(name = "id_util_maj", length = 18)
    private Long updaterCode;

    @Column(name = "id_util_suppr", length = 18)
    private Long deleterCode;

    @Column(name = "boo_suppr", nullable = false)
    private Boolean isDeleted = false;

    /* spare columns to be used for any emergency */
    @Column(name = "temp_data")
    private String tempData;

    private String extra;

    /* --------- Automatic actions on create, update, delete ---------- */

    @PrePersist
    protected void beforePersist() {
        if (creationDate == null) this.creationDate = ZonedDateTime.now();
//        this.creatorCode = resolveUserIdFromSecurityContext();
    }

    @PreUpdate
    protected void beforeUpdate() {
        this.updateDate = ZonedDateTime.now();
//        this.updaterCode = resolveUserIdFromSecurityContext();
    }

    @PreRemove
    protected void preRemove() {
        this.updateDate = ZonedDateTime.now();
        this.deleteDate = ZonedDateTime.now();
//        this.deleterCode = resolveUserIdFromSecurityContext();
    }

//    @PrePersist
//    protected void beforePersist() {
//        if (creationDate == null) this.creationDate = ZonedDateTime.now();
//
//        /* Automatically fill the creatorCode from Spring security "SecurityContextHolder" */
//        if (SecurityContextHolder.getContext().getAuthentication() != null) {
//            // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            // UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
//            this.creatorCode = principal.getUserId();
//        }
//    }

//    @PreUpdate
//    protected void beforeUpdate() {
//        this.updateDate = ZonedDateTime.now();
//
//        /* Automatically fill the updaterCode from Spring security "SecurityContextHolder" */
//        if (SecurityContextHolder.getContext().getAuthentication() != null) {
//            // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            // UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
//            this.updaterCode = principal.getUserId();
//        }
//    }


//    /**
//     * This method will never be used because we never perform DELETE actions against the DB</br>
//     * We just set some properties and make a PERSIST action instead
//     */
//    @PreRemove
//    protected void preRemove() {
//        this.updateDate = ZonedDateTime.now();
//        this.deleteDate = ZonedDateTime.now();
//
//        /* Automatically fill the deleter code from Spring security "SecurityContextHolder" */
//        if (SecurityContextHolder.getContext().getAuthentication() != null) {
//            // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            // UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
//            this.deleterCode = principal.getUserId();
//        }
//    }

    /* --------- custom methods ---------- */

    public Boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

//    // Called once at app start or bean initialization
//    @Setter @Transient
//    private static UtilisateurRepository utilisateurRepository;
//
//    private Long resolveUserIdFromSecurityContext() {
//        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || !authentication.isAuthenticated()) return null;
//
//        Object principal = authentication.getPrincipal();
//
//        if (principal instanceof UserPrincipal userPrincipal) {
//            return userPrincipal.getUserId();
//
//        } else if (principal instanceof UserDetails userDetails && utilisateurRepository != null) {
//            return utilisateurRepository.findOne(
//                    UtilisateurRepository.isDeleted(false)
//                                         .and(UtilisateurRepository.loginEquals(userDetails.getUsername()))
//                                                ).map(Utilisateur::getId).orElse(null);
//        }
//
//        return null;
//    }


}

