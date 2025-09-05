package ra.edu.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ra.edu.dto.ResponseData;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseData> handleNotFoundException(NotFoundException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ResponseData.builder()
                .success(false)
                .message(ex.getMessage())
                .build(), HttpStatus.NOT_FOUND); //404
    }
   // nguoi dung chua xac thuc
    @ExceptionHandler( AuthorizationDeniedException.class)
    public ResponseEntity<ResponseData> handleAuthenticationException(AuthorizationDeniedException ex) {
        log.error("Error: "+ex.getMessage());
        return new ResponseEntity<>(ResponseData.builder()
                .success(false)
                .message("Error: "+ex.getMessage())
                .build(), HttpStatus.UNAUTHORIZED); //401 chua xac thuc
    }
    // nguoi dung ko co quyen
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseData> handleAccessDeniedException(AccessDeniedException ex) {
        log.error("Error: "+ex.getMessage());
        return new ResponseEntity<>(ResponseData.builder()
                .success(false)
                .message("Error: "+ex.getMessage())
                .build(), HttpStatus.FORBIDDEN); //403 ko cos quyeenf
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        log.error("Error: "+ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
