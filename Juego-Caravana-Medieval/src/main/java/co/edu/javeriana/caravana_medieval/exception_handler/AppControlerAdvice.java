package co.edu.javeriana.caravana_medieval.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import co.edu.javeriana.caravana_medieval.dto.ErrorDTO;

import java.util.NoSuchElementException;

@ControllerAdvice
public class AppControlerAdvice {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorDTO> handleNotFoundException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Resource does not exist"));
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO("Error interno del servidor: " + e.getMessage()));
    }
}
