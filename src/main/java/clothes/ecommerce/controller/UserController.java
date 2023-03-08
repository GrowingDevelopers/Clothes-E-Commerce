package clothes.ecommerce.controller;

import clothes.ecommerce.domain.user.User;
import clothes.ecommerce.domain.user.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public String userTest() {
        User user = new User();

        user.setUserNumber(1L);
        user.setUserName("최서웅");
        user.setUserId("cyr0331");
        user.setUserPassword("1234");
        user.setBankAccount("하나은행 620-252096-791");
        user.setUserAddress("서울특별시 구로구 구로동");
        user.setBankAccountAmount(new BigDecimal(500000));

        userRepository.save(user);

        return "Ok";
    }
}
