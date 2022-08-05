package com.lucasbarbosa.libraryapi.driver.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
/** @author Lucas Barbosa on 27/06/2021 */
@Configuration
public class MessageConfig {

  @Bean
  public MessageSource messageSource() {
    var resourceBundleMessageSource = new ResourceBundleMessageSource();
    resourceBundleMessageSource.setBasename("messages");
    return resourceBundleMessageSource;
  }
}
