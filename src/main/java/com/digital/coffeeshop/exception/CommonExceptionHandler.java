package com.digital.coffeeshop.exception;

import com.digital.coffeeshop.util.Constant;
import java.time.Instant;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Handle all exceptions and java bean validation.
 *
 * @author Niranjan Thilakarathna
 */
@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger local_logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException exception,
      WebRequest request) {
    return getExceptionResponseEntity(exception, HttpStatus.NOT_FOUND, request,
        Collections.singletonList(exception.getLocalizedMessage()));
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException exception,
      HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    List<String> validationErrors = exception.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(error -> error.getField() + Constant.FIELD_ERROR_SEPARATOR + error.getDefaultMessage())
        .collect(Collectors.toList());
    return getExceptionResponseEntity(exception, HttpStatus.BAD_REQUEST, request, validationErrors);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException exception,
      HttpHeaders headers, HttpStatus status,
      WebRequest request) {
    return getExceptionResponseEntity(exception, status, request,
        Collections.singletonList(exception.getLocalizedMessage()));
  }

  /**
   * Include detail information about the exception
   */
  private ResponseEntity<Object> getExceptionResponseEntity(final Exception exception,
      final HttpStatus status,
      final WebRequest request,
      final List<String> errors) {
    final Map<String, Object> body = new LinkedHashMap<>();
    final String path = request.getDescription(false);
    body.put(Constant.TIMESTAMP, Instant.now());
    body.put(Constant.STATUS, status.value());
    body.put(Constant.ERRORS, errors);
    body.put(Constant.TYPE, exception.getClass().getSimpleName());
    body.put(Constant.PATH, path);
    body.put(Constant.MESSAGE, getMessageForStatus(status));
    final String errorsMessage = CollectionUtils.isNotEmpty(errors) ?
        errors.stream().filter(StringUtils::isNotEmpty)
            .collect(Collectors.joining(Constant.LIST_JOIN_DELIMITER))
        : status.getReasonPhrase();
    local_logger.error(Constant.ERRORS_FOR_PATH, errorsMessage, path);
    return new ResponseEntity<>(body, status);
  }

  private String getMessageForStatus(HttpStatus status) {
    switch (status) {
      case UNAUTHORIZED:
        return Constant.ACCESS_DENIED;
      case BAD_REQUEST:
        return Constant.INVALID_REQUEST;
      default:
        return status.getReasonPhrase();
    }
  }

}
