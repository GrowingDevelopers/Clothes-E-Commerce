package clothes.ecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    /* 400 BAD_REQUEST : 잘못된 요청 */
    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_AUTH_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 요청이거나 인증되지 않은 사용자입니다."),

    /* 404 NOT_FOUND : 리소스를 찾을 수 없음 */
    DATA_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 정보를 찾을 수 없습니다."),
    DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "데이터가 이미 존재합니다.");

    /**/

    private final HttpStatus httpStatus;
    private final String message;
}
