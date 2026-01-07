package com.chace.microservice.model.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Province.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Province_ extends com.chace.microservice.model.entity._AbstractCommonModel_ {

	public static final String DESCRIPTION_PROVINCE = "descriptionProvince";
	public static final String CODE_PROVINCE = "codeProvince";
	public static final String LONG_PROVINCE = "longProvince";
	public static final String LAT_PROVINCE = "latProvince";

	
	/**
	 * @see com.chace.microservice.model.entity.Province#descriptionProvince
	 **/
	public static volatile SingularAttribute<Province, String> descriptionProvince;
	
	/**
	 * @see com.chace.microservice.model.entity.Province#codeProvince
	 **/
	public static volatile SingularAttribute<Province, String> codeProvince;
	
	/**
	 * @see com.chace.microservice.model.entity.Province
	 **/
	public static volatile EntityType<Province> class_;
	
	/**
	 * @see com.chace.microservice.model.entity.Province#longProvince
	 **/
	public static volatile SingularAttribute<Province, Double> longProvince;
	
	/**
	 * @see com.chace.microservice.model.entity.Province#latProvince
	 **/
	public static volatile SingularAttribute<Province, Double> latProvince;

}

