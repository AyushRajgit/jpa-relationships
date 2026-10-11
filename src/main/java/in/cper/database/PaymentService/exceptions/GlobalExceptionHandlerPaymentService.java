package in.cper.database.PaymentService.exceptions;

import in.cper.database.PaymentService.dtoMapper.ExceptionDTOs;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerPaymentService {

    @ExceptionHandler(InsufficientBalance.class)
    public ResponseEntity<ExceptionDTOs> handleInsufficientBalance(InsufficientBalance e, HttpServletRequest request) {
        ExceptionDTOs exceptionDTOs = new ExceptionDTOs(
                HttpStatus.UNPROCESSABLE_CONTENT.value(),
                HttpStatus.UNPROCESSABLE_CONTENT.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(exceptionDTOs);
    }

    @ExceptionHandler(UserAccountNotFound.class)
    public ResponseEntity<ExceptionDTOs> handleInvalidUserAccount(UserAccountNotFound e, HttpServletRequest request) {
        ExceptionDTOs exceptionDTOs = new ExceptionDTOs(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTOs);
    }

}
