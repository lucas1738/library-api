package com.lucasbarbosa.libraryapi.driver.converter;

import com.lucasbarbosa.libraryapi.model.enums.BookGenreEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.lucasbarbosa.libraryapi.model.enums.BookGenreEnum.findByInitial;

/** @author Lucas Barbosa on 27/06/2021 */
@Converter(autoApply = true)
public class BookGenreEnumCharConverter implements AttributeConverter<BookGenreEnum, String> {

  @Override
  public String convertToDatabaseColumn(BookGenreEnum bookGenre) {
    return bookGenre.getInitial();
  }

  @Override
  public BookGenreEnum convertToEntityAttribute(String genre) {
    return findByInitial(genre);
  }
}
