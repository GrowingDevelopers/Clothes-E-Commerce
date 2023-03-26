package clothes.ecommerce.service;

import clothes.ecommerce.domain.user.User;
import clothes.ecommerce.domain.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserMapper userMapper;

    public Optional<User> userLogin(User user) {
        return userMapper.login(user);
    }
}
