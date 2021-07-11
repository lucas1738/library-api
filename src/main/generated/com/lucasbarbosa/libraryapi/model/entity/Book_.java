package com.lucasbarbosa.libraryapi.model.entity;

import com.lucasbarbosa.libraryapi.model.enums.BookGenreEnum;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Book.class)
public abstract class Book_ {

	public static volatile SingularAttribute<Book, LocalDateTime> updateDate;
	public static volatile SingularAttribute<Book, Integer> numberPages;
	public static volatile SingularAttribute<Book, String> author;
	public static volatile SingularAttribute<Book, String> isbn;
	public static volatile SingularAttribute<Book, String> title;
	public static volatile SingularAttribute<Book, LocalDateTime> creationDate;
	public static volatile SingularAttribute<Book, BookGenreEnum> bookGenre;

	public static final String UPDATE_DATE = "updateDate";
	public static final String NUMBER_PAGES = "numberPages";
	public static final String AUTHOR = "author";
	public static final String ISBN = "isbn";
	public static final String TITLE = "title";
	public static final String CREATION_DATE = "creationDate";
	public static final String BOOK_GENRE = "bookGenre";

}

