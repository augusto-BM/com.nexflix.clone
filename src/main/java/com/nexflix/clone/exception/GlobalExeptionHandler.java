package com.nexflix.clone.exception;

import org.apache.catalina.connector.ClientAbortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.async.AsyncRequestNotUsableException;

import java.time.Instant;
import java.util.Map;

@ControllerAdvice
public class GlobalExeptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExeptionHandler.class);

    @ExceptionHandler(BadCredentialExeption.class)
    public ResponseEntity<Map<String, Object>> handleBadCredential(BadCredentialExeption ex) {
        log.warn("Credencial inválida: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(AccountDesactivatedExeption.class)
    public ResponseEntity<Map<String, Object>> handleAccountDesactivated(AccountDesactivatedExeption ex) {
        log.warn("Cuenta desactivada: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsExeption.class)
    public ResponseEntity<Map<String, Object>> handleEmailAlreadyExists(EmailAlreadyExistsExeption ex) {
        log.warn("Email ya existe: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(EmailNotVerifiedExeption.class)
    public ResponseEntity<Map<String, Object>> handleEmailNotVerified(EmailNotVerifiedExeption ex) {
        log.warn("Email no verificado: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(EmailSendingExeption.class)
    public ResponseEntity<Map<String, Object>> handleEmailSending(EmailSendingExeption ex) {
        log.error("Error enviando email: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsExeption.class)
    public ResponseEntity<Map<String, Object>> handleInvalidCredentials(InvalidCredentialsExeption ex) {
        log.warn("Credenciales inválidas: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(InvalidRoleExeption.class)
    public ResponseEntity<Map<String, Object>> handleInvalidRole(InvalidRoleExeption ex) {
        log.warn("Rol inválido: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InvalidTokenExeption.class)
    public ResponseEntity<Map<String, Object>> handleInvalidToken(InvalidTokenExeption ex) {
        log.warn("Token inválido: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundExeption.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundExeption ex) {
        log.warn("Recurso no encontrado: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .orElse("Error de validación");

        return buildResponse(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler({AsyncRequestNotUsableException.class, ClientAbortException.class})
    public ResponseEntity<Map<String, Object>> handleClientAbort(Exception ex) {
        log.debug("El cliente perdió la conexión durante la transmisión: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        log.error("Error inesperado: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
        Map<String, Object> body = Map.of(
                "timestamp", Instant.now(),
                "status", status.value(),
                "error", message
        );

        return ResponseEntity.status(status).body(body);
    }
}