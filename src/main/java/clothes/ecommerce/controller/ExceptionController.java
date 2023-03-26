package clothes.ecommerce.controller;

import clothes.ecommerce.exception.CustomException;
import clothes.ecommerce.responseentity.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler({
                    CustomException.class
            })
    public ResponseEntity<ErrorResponse> HandleCustomException(CustomException e) {
        log.warn("ErrorCode : {}", e.getErrorCode());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
