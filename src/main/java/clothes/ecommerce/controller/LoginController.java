package clothes.ecommerce.controller;

import clothes.ecommerce.domain.user.User;
import clothes.ecommerce.exception.CustomException;
import clothes.ecommerce.exception.ErrorCode;
import clothes.ecommerce.responseentity.Message;
import clothes.ecommerce.responseentity.MessageResponse;
import clothes.ecommerce.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> userLogin(@RequestBody User user, HttpServletRequest request) {
        log.info("input userId : {}", user.getUserId());
        log.info("input userPassword : {}", user.getUserPassword());

        Optional<User> optionalUser = Optional.ofNullable(loginService.userLogin(user)
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_AUTH_TOKEN)));

        /* 로그인 성공 */
        if(optionalUser.get().getUserId().equals(user.getUserId())
                && optionalUser.get().getUserPassword().equals(user.getUserPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", optionalUser.get().getUserId());
            session.setMaxInactiveInterval(60 * 60);

            return MessageResponse.toLoginResponseEntity(user);
        }

        else {
            throw new CustomException(ErrorCode.INVALID_AUTH_TOKEN);
        }

    }

    @PostMapping("/logout")
    public String userLogout(HttpSession session) {
        Object userId = session.getAttribute("userId");
        log.info("로그아웃된 Id : {}", userId);
        session.invalidate();
        return "로그아웃되었습니다.";
    }
}
