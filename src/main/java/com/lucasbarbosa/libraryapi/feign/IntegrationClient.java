package com.lucasbarbosa.libraryapi.feign;

import com.lucasbarbosa.libraryapi.driver.exception.custom.FeignIntegrationException;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.lucasbarbosa.libraryapi.driver.utils.ExceptionUtils.retrieveExceptionClassName;

/** @author Lucas Barbosa on 08/08/2021 */
public interface IntegrationClient<R, S> {

  default Optional<R> retrieveClient(Optional<Map<String, Object>> params) {
    try {
      return writeClientIntegration(params);
    } catch (Exception exception) {
      logClientFallback(exception);
      throw new FeignIntegrationException(this.getClass().getSimpleName());
    }
  }

  Optional<R> writeClientIntegration(Optional<Map<String, Object>> params);

  Class<S> identify();

  default void logClientFallback(Exception exception) {

    Logger logger = Logger.getLogger(identify().getName());
    logger.log(
        Level.WARNING,
        String.format(
            "m=retrieveClient exception=%s exceptionName=%s",
            exception, retrieveExceptionClassName(exception)));
  }
}
