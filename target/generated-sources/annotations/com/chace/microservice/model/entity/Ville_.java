package com.chace.microservice.model.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Ville.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Ville_ extends com.chace.microservice.model.entity._AbstractCommonModel_ {

	public static final String PROVINCE = "province";
	public static final String NOM_VILLE = "nomVille";
	public static final String LAT_VILLE = "latVille";
	public static final String LONG_VILLE = "longVille";
	public static final String CODE_VILLE = "codeVille";

	
	/**
	 * @see com.chace.microservice.model.entity.Ville#province
	 **/
	public static volatile SingularAttribute<Ville, Province> province;
	
	/**
	 * @see com.chace.microservice.model.entity.Ville#nomVille
	 **/
	public static volatile SingularAttribute<Ville, String> nomVille;
	
	/**
	 * @see com.chace.microservice.model.entity.Ville#latVille
	 **/
	public static volatile SingularAttribute<Ville, Double> latVille;
	
	/**
	 * @see com.chace.microservice.model.entity.Ville#longVille
	 **/
	public static volatile SingularAttribute<Ville, Double> longVille;
	
	/**
	 * @see com.chace.microservice.model.entity.Ville#codeVille
	 **/
	public static volatile SingularAttribute<Ville, String> codeVille;
	
	/**
	 * @see com.chace.microservice.model.entity.Ville
	 **/
	public static volatile EntityType<Ville> class_;

}

