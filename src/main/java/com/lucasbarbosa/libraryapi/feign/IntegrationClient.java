package com.lucasbarbosa.libraryapi.feign;

import static com.lucasbarbosa.libraryapi.driver.utils.ExceptionUtils.retrieveExceptionClassName;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Lucas Barbosa on 08/08/2021
 */
public interface IntegrationClient<R, S> {

  default Optional<R> retrieveClient(Optional<Map<String, Object>> params) {
    try {
      return writeClientIntegration(params);
    } catch (Exception exception) {
      logClientFallback(exception);
      //      throw new FeignIntegrationException(this.getClass().getSimpleName());
    }
      return writeClientIntegration(params);
  }

  Optional<R> writeClientIntegration(Optional<Map<String, Object>> params);

  Class<S> identify();

  Optional<R> value();

  default long timeout(){
    return 500L;
  }

  default TimeUnit timeUnit(){
    return TimeUnit.MICROSECONDS;
  }

  default void logClientFallback(Exception exception) {

    Logger logger = Logger.getLogger(identify().getName());
    logger.log(
        Level.WARNING,
        String.format(
            "m=retrieveClient exception=%s exceptionName=%s",
            exception, retrieveExceptionClassName(exception)));
  }
}
