package src.handler;

import src.exception.CartServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CartServiceHandler {

    @ExceptionHandler(CartServiceException.class)
    public ResponseEntity<String> handleProductServiceException(CartServiceException ex) {
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }
}
