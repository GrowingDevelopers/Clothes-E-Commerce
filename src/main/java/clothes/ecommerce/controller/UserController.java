package clothes.ecommerce.controller;

import clothes.ecommerce.domain.user.User;
import clothes.ecommerce.exception.CustomException;
import clothes.ecommerce.exception.ErrorCode;
import clothes.ecommerce.responseentity.MessageResponse;
import clothes.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 사용자(회원) controller
 */

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 회원가입
     * @param user
     *
     * @return MessageResponse
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        try {
            duplicateValidateUser(user);
            userService.joinUser(user);
        } catch (DataIntegrityViolationException e) {
            e.getStackTrace();
        }

        return MessageResponse.toJoinResponseEntity(user);
    }

    private void duplicateValidateUser(User user) {
        userService.findUserByUserId(user.getUserId())
                .ifPresent((u -> {
                    throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
                }));
    }

    /**
     * 유저 번호로 유저 검색
     * @param userNumber
     * @return 유저의 정보 조회(유저 정보, 메시지)
     */

    @GetMapping("/search/userNumber")
    public ResponseEntity<?> searchByUserNumber(@RequestBody Long userNumber) {
        User user = userService.findUserByUserNumber(userNumber)
                .orElseThrow(() -> new CustomException(ErrorCode.DATA_NOT_FOUND));

        return MessageResponse.toFindResponseEntity(user);
    }

    /**
     * 유저 아이디로 유저 검색
     * @param userId
     * @return 유저의 정보 조회(유저 정보, 메시지)
     */
    @GetMapping("/search/userId")
    public ResponseEntity<?> searchByUserId(@RequestBody String userId) {
        User user = userService.findUserByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.DATA_NOT_FOUND));

        return MessageResponse.toFindResponseEntity(user);
    }

    /**
     * 유저 전체 검색
     *
     * @return 유저의 전체 정보 조회
     */
    @GetMapping("/search/user")
    public List<User> searchUserAll() {
        return userService.findUserAll();
    }

    /**
     * 유저 번호로 유저 정보 업데이트
     * @param user
     * @return 유저 정보 갱신 완료
     */
    @PostMapping("/update/user")
    public String update(@RequestBody User user) {
        userService.updateUserByUserNumber(user);
        return "유저 정보가 갱신되었습니다.";
    }

    /**
     * 유저 번호로 유저 정보 삭제
     * @param userNumber
     * @return 유저 정보 삭제 완료
     */
    @PostMapping("/delete/userNumber")
    public String delete(@RequestBody Long userNumber) {
        userService.deleteUserByUserNumber(userNumber);
        return "유저 정보가 삭제되었습니다.";
    }
}
