package in.cper.database.ECommerceOrderEngine.exceptions;

import in.cper.database.ECommerceOrderEngine.dto.ExceptionDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleUserNotFoundException(NotFoundException e, HttpServletRequest request) {
        ExceptionDTO dto = new ExceptionDTO(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
    }

    @ExceptionHandler(EmptyRequestException.class)
    public ResponseEntity<ExceptionDTO> handleEmptyUserException(EmptyRequestException e, HttpServletRequest request) {
        ExceptionDTO dto = new ExceptionDTO(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
    }

    @ExceptionHandler(UnableToSaveException.class)
    public ResponseEntity<ExceptionDTO> handleUserUnableToSaveException(UnableToSaveException e, HttpServletRequest request) {
        ExceptionDTO dto = new ExceptionDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
    }

    @ExceptionHandler(NoStockAvailableException.class)
    public ResponseEntity<ExceptionDTO> handleUserNoStockAvailableException(NoStockAvailableException e, HttpServletRequest request) {
        ExceptionDTO dto = new ExceptionDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
    }
}
