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
}
