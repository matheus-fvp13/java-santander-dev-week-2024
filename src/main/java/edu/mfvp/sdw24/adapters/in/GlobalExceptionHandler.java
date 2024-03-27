package edu.mfvp.sdw24.adapters.in;

import edu.mfvp.sdw24.domain.exceptions.ChampionNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleDomainException(Exception domainError) {
        var errorResponse = new ApiError("Error! Please, contact admin");
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    @ExceptionHandler(ChampionNotFoundException.class)
    public ResponseEntity<ApiError> handleDomainException(ChampionNotFoundException domainError) {
        var errorResponse = new ApiError(domainError.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    public record ApiError(String message) {}
}
