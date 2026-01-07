package com.chace.microservice.model.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CentreTechnique.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class CentreTechnique_ extends com.chace.microservice.model.entity._AbstractCommonModel_ {

	public static final String LAT_CENTRE_TECHNIQUE = "latCentreTechnique";
	public static final String VILLE = "ville";
	public static final String DESCRIPTION_CENTRE_TECHNIQUE = "descriptionCentreTechnique";
	public static final String CODE_CENTRE_TECHNIQUE = "codeCentreTechnique";
	public static final String LONG_CENTRE_TECHNIQUE = "longCentreTechnique";

	
	/**
	 * @see com.chace.microservice.model.entity.CentreTechnique#latCentreTechnique
	 **/
	public static volatile SingularAttribute<CentreTechnique, Double> latCentreTechnique;
	
	/**
	 * @see com.chace.microservice.model.entity.CentreTechnique#ville
	 **/
	public static volatile SingularAttribute<CentreTechnique, Ville> ville;
	
	/**
	 * @see com.chace.microservice.model.entity.CentreTechnique#descriptionCentreTechnique
	 **/
	public static volatile SingularAttribute<CentreTechnique, String> descriptionCentreTechnique;
	
	/**
	 * @see com.chace.microservice.model.entity.CentreTechnique#codeCentreTechnique
	 **/
	public static volatile SingularAttribute<CentreTechnique, String> codeCentreTechnique;
	
	/**
	 * @see com.chace.microservice.model.entity.CentreTechnique#longCentreTechnique
	 **/
	public static volatile SingularAttribute<CentreTechnique, Double> longCentreTechnique;
	
	/**
	 * @see com.chace.microservice.model.entity.CentreTechnique
	 **/
	public static volatile EntityType<CentreTechnique> class_;

}

