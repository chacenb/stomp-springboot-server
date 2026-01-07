package com.chace.microservice.model.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.MappedSuperclassType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.ZonedDateTime;

@StaticMetamodel(_AbstractCommonModel.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class _AbstractCommonModel_ {

	public static final String UPDATER_CODE = "updaterCode";
	public static final String UPDATE_DATE = "updateDate";
	public static final String CREATOR_CODE = "creatorCode";
	public static final String DELETER_CODE = "deleterCode";
	public static final String IS_DELETED = "isDeleted";
	public static final String TEMP_DATA = "tempData";
	public static final String EXTRA = "extra";
	public static final String ID = "id";
	public static final String CREATION_DATE = "creationDate";
	public static final String DELETE_DATE = "deleteDate";

	
	/**
	 * @see com.chace.microservice.model.entity._AbstractCommonModel#updaterCode
	 **/
	public static volatile SingularAttribute<_AbstractCommonModel, Long> updaterCode;
	
	/**
	 * @see com.chace.microservice.model.entity._AbstractCommonModel#updateDate
	 **/
	public static volatile SingularAttribute<_AbstractCommonModel, ZonedDateTime> updateDate;
	
	/**
	 * @see com.chace.microservice.model.entity._AbstractCommonModel#creatorCode
	 **/
	public static volatile SingularAttribute<_AbstractCommonModel, Long> creatorCode;
	
	/**
	 * @see com.chace.microservice.model.entity._AbstractCommonModel#deleterCode
	 **/
	public static volatile SingularAttribute<_AbstractCommonModel, Long> deleterCode;
	
	/**
	 * @see com.chace.microservice.model.entity._AbstractCommonModel#isDeleted
	 **/
	public static volatile SingularAttribute<_AbstractCommonModel, Boolean> isDeleted;
	
	/**
	 * @see com.chace.microservice.model.entity._AbstractCommonModel#tempData
	 **/
	public static volatile SingularAttribute<_AbstractCommonModel, String> tempData;
	
	/**
	 * @see com.chace.microservice.model.entity._AbstractCommonModel#extra
	 **/
	public static volatile SingularAttribute<_AbstractCommonModel, String> extra;
	
	/**
	 * @see com.chace.microservice.model.entity._AbstractCommonModel#id
	 **/
	public static volatile SingularAttribute<_AbstractCommonModel, Long> id;
	
	/**
	 * @see com.chace.microservice.model.entity._AbstractCommonModel#creationDate
	 **/
	public static volatile SingularAttribute<_AbstractCommonModel, ZonedDateTime> creationDate;
	
	/**
	 * @see com.chace.microservice.model.entity._AbstractCommonModel
	 **/
	public static volatile MappedSuperclassType<_AbstractCommonModel> class_;
	
	/**
	 * @see com.chace.microservice.model.entity._AbstractCommonModel#deleteDate
	 **/
	public static volatile SingularAttribute<_AbstractCommonModel, ZonedDateTime> deleteDate;

}

