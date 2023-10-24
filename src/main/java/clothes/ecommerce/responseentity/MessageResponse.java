package clothes.ecommerce.responseentity;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class MessageResponse {
    private int status;
    private String code;
    private String message;
    private Object data;

    public static ResponseEntity<MessageResponse> toLoginResponseEntity(Object data) {
        return ResponseEntity
                .ok()
                .body(MessageResponse.builder()
                        .status(HttpStatus.OK.value())
                        .code(HttpStatus.OK.name())
                        .data(data)
                        .message("로그인에 성공하였습니다.")
                        .build()
                );
    }

    public static ResponseEntity<MessageResponse> toJoinResponseEntity(Object data) {
        return ResponseEntity
                .ok()
                .body(MessageResponse.builder()
                        .status(HttpStatus.OK.value())
                        .code(HttpStatus.OK.name())
                        .data(data)
                        .message("회원가입이 완료되었습니다.")
                        .build()
                );
    }

    public static ResponseEntity<MessageResponse> toFindResponseEntity(Object data) {
        return ResponseEntity
                .ok()
                .body(MessageResponse.builder()
                        .status(HttpStatus.OK.value())
                        .code(HttpStatus.OK.name())
                        .data(data)
                        .message("검색에 성공하였습니다.")
                        .build()
                );
    }
}
