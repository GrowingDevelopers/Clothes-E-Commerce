package clothes.ecommerce.controller;

import clothes.ecommerce.domain.user.User;
import clothes.ecommerce.domain.user.UserMapper;
import clothes.ecommerce.message.Message;
import clothes.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * 사용자(회원) controller
 */

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     *  회원가입 진행
     *  if 검증 완료 시
     * @return 회원가입 완료
     * else
     * @return 회원가입 실패
     */
    @PostMapping("/signup")
    public String signUp(@RequestBody User user) {
        if(userService.joinUser(user)) {
            return "회원가입이 완료되었습니다.";
        }

        else {
            return "이미 존재하는 아이디입니다. 다시 아이디를 입력해주세요.";
        }
    }

    /**
     * 유저 번호로 유저 검색
     *
     * @return 유저의 정보 조회(유저 정보, 메시지)
     */

    @GetMapping("/search/userNumber")
    public ResponseEntity<Message> searchByUserNumber(@RequestBody Long userNumber) {
        Optional<User> user = userService.findUserByUserNumber(userNumber);
        Message message = new Message();

        return getMessageResponseEntity(user, message);
    }

    /**
     * 유저 이름으로 유저 검색
     *
     * @return 유저리스트 정보 조회(유저 정보, 메시지)
     */

    @GetMapping("/search/userName")
    public ResponseEntity<Message> searchByUserName(@RequestBody String userName) {
        List<User> users = userService.findUserByUserName(userName);
        Message message = new Message();

        return getMessageResponseEntity(users, message);
    }

    /**
     * 유저 아이디로 유저 검색
     *
     * @return 유저의 정보 조회(유저 정보, 메시지)
     */
    @GetMapping("/search/userId")
    public ResponseEntity<Message> searchByUserId(@RequestBody String userId) {
        Optional<User> user = userService.findUserByUserId(userId);
        Message message = new Message();

        return getMessageResponseEntity(user, message);
    }

    /**
     * user의 null 여부에 따라 ResponseEntity를 반환하는 메서드
     *
     * @return ResponseEntity<>()
     */
    private ResponseEntity<Message> getMessageResponseEntity(Object object, Message message) {
        if(object instanceof Optional) {
            Optional<User> user = (Optional<User>) object;
            if(user.orElse(null) == null) {
                message.setMessage("존재하지 않는 데이터입니다.");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }

            else {
                message.setMessage("검색에 성공하였습니다.");
                message.setData(user);
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
        }

        if(object instanceof List) {
            List<User> users = (List<User>) object;
            if(users.isEmpty()) {
                message.setMessage("존재하지 않는 데이터입니다.");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }

            else {
                message.setMessage("검색에 성공하였습니다.");
                message.setData(users);
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
        }

        return null;
    }

    /**
     * 유저 전체 검색
     *
     * @return 유저의 전체 정보 조회
     */

    @GetMapping("/search")
    public List<User> searchAll() {
        return userService.findUserAll();
    }

    /**
     * 유저 번호로 유저 정보 업데이트
     *
     * @return 유저 정보 갱신 완료
     */
    @PostMapping("/update")
    public String update(@RequestBody User user) {
        userService.updateUserByUserNumber(user);
        return "정보가 갱신되었습니다.";
    }

    /**
     * 유저 번호로 유저 정보 삭제
     *
     * @return 유저 정보 삭제 완료
     */
    @PostMapping("/delete/userNumber")
    public String delete(@RequestBody Long userNumber) {
        userService.deleteUserByUserNumber(userNumber);
        return "정보가 삭제되었습니다.";
    }
}
