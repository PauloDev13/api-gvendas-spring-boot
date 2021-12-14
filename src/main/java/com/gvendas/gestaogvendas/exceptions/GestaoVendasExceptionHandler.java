package com.gvendas.gestaogvendas.exceptions;

import javassist.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class GestaoVendasExceptionHandler extends ResponseEntityExceptionHandler {

  public static final String CONSTANT_VALIDATION_NOT_BLANK = "NotBlank";
  public static final String CONSTANT_VALIDATION_NOT_NULL = "NotNull";
  public static final String CONSTANT_VALIDATION_LENGTH = "Length";

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

    List<ErrorHandler> errors = listOfErrors(ex.getBindingResult());

    return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  public ResponseEntity<Object> handleEmptyResultDataAccessException(
      EmptyResultDataAccessException ex, WebRequest request) {
    String userMessage = "Recurso não encontrado";
    String developerMessage = ex.toString();
    List<ErrorHandler> errors = List.of(new ErrorHandler(userMessage, developerMessage));
    return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);

  }

  @ExceptionHandler(BusinessRulesException.class)
  public ResponseEntity<Object> handleDuplicateCategoryException(
      BusinessRulesException ex, WebRequest request) {
    String userMessage = ex.getMessage();
    String developerMessage = ex.getMessage();
    List<ErrorHandler> errors = List.of(new ErrorHandler(userMessage, developerMessage));
    return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.CONFLICT, request);

  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Object> handleDataIntegrityViolationException(
      DataIntegrityViolationException ex, WebRequest request) {
    String userMessage = "Recurso não encontrado";
    String developerMessage = ex.toString();
    List<ErrorHandler> errors = List.of(new ErrorHandler(userMessage, developerMessage));
    return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

  }

  private List<ErrorHandler> listOfErrors(BindingResult bindingResult) {
    List<ErrorHandler> errors = new ArrayList<>();
    bindingResult.getFieldErrors().forEach(fieldError -> {
      String userMessage = messageErrorUser(fieldError);
      String developerMessage = fieldError.toString();
      errors.add(new ErrorHandler(userMessage, developerMessage));
    });
    return errors;
  }

  private String messageErrorUser(FieldError fieldError) {
    if (Objects.equals(fieldError.getCode(), CONSTANT_VALIDATION_NOT_BLANK)) {
      return Objects.requireNonNull(fieldError.getDefaultMessage()).concat(" é obrigatório");
    }

    if (Objects.equals(fieldError.getCode(), CONSTANT_VALIDATION_LENGTH)) {
      return Objects.requireNonNull(fieldError.getDefaultMessage()).concat(
          String.format(" deve ter entre %s e %s caracteres",
          Objects.requireNonNull(fieldError.getArguments())[2], fieldError.getArguments()[1]));
    }

    if (Objects.equals(fieldError.getCode(), CONSTANT_VALIDATION_NOT_NULL)) {
      return Objects.requireNonNull(fieldError.getDefaultMessage()).concat(" é obrigatório");
    }
    return fieldError.toString();
  }
}
