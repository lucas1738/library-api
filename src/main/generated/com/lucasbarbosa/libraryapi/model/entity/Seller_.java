package com.lucasbarbosa.libraryapi.model.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Seller.class)
public abstract class Seller_ {

	public static volatile SingularAttribute<Seller, String> documentNumber;
	public static volatile SingularAttribute<Seller, String> description;
	public static volatile SingularAttribute<Seller, String> id;
	public static volatile SingularAttribute<Seller, String> key;

	public static final String DOCUMENT_NUMBER = "documentNumber";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String KEY = "key";

}

