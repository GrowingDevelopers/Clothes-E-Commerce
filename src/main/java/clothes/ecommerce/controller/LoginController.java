package clothes.ecommerce.controller;

import clothes.ecommerce.domain.user.User;
import clothes.ecommerce.exception.CustomException;
import clothes.ecommerce.exception.ErrorCode;
import clothes.ecommerce.responseentity.MessageResponse;
import clothes.ecommerce.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginService loginService;
    private final int SESSION_TIME_LIMIT = 60 * 60;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> userLogin(@RequestBody User user, HttpServletRequest request) {
        log.info("input userId : {}", user.getUserId());
        log.info("input userPassword : {}", user.getUserPassword());

        User loginUser = loginService.userLogin(user)
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_AUTH_TOKEN));

        /* 로그인 성공 */
        if(loginUser.getUserId().equals(user.getUserId())
                && loginUser.getUserPassword().equals(user.getUserPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", loginUser.getUserId());
            session.setMaxInactiveInterval(SESSION_TIME_LIMIT);

            return MessageResponse.toLoginResponseEntity(loginUser);
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
