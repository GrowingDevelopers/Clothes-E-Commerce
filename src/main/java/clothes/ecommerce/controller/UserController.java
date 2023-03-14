package clothes.ecommerce.controller;

import clothes.ecommerce.domain.user.User;
import clothes.ecommerce.domain.user.UserMapper;
import clothes.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
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
     * @return 회원가입 완료
     */
    @PostMapping("/signup")
    public String signUp(@RequestBody User user) {
        userService.joinUser(user);

        return "회원가입이 완료되었습니다.";
    }

    /**
     * 유저 번호로 유저 검색
     *
     * @return 유저의 정보 조회
     */

    @GetMapping("/search/userNumber")
    public Optional<User> searchByUserNumber(@RequestBody Long userNumber) {
        return userService.findUserByUserNumber(userNumber);
    }

    /**
     * 유저 이름으로 유저 검색
     *
     * @return 유저의 정보 조회
     */

    @GetMapping("/search/userName")
    public List<User> searchByUserName(@RequestBody String userName) {
        return userService.findUserByUserName(userName);
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
     * @return 정보 갱신 완료
     */
    @PostMapping("/update")
    public String update(@RequestBody User user) {
        userService.updateUserByUserNumber(user);
        return "정보가 갱신되었습니다.";
    }
}
