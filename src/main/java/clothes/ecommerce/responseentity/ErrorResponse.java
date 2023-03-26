package clothes.ecommerce.responseentity;

import clothes.ecommerce.exception.ErrorCode;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class ErrorResponse {
    private int status;
    private String code;
    private String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .code(errorCode.name())
                        .message(errorCode.getMessage())
                                .build()
                        );
    }
}
