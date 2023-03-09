package clothes.ecommerce.service;

import clothes.ecommerce.domain.user.User;
import clothes.ecommerce.domain.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    // 회원 가입 메소드
     public void joinUser(User user) {
         userMapper.createUser(user);
     }
}
