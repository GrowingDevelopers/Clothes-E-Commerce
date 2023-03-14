package clothes.ecommerce.service;

import clothes.ecommerce.domain.user.User;
import clothes.ecommerce.domain.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    // 회원 가입 메소드
     public void joinUser(User user) {
         userMapper.createUser(user);
     }

     // 유저 번호로 유저 검색
    public Optional<User> findUserByUserNumber(Long userNumber) {
        return userMapper.findByUserNumber(userNumber);
    }
    // 유저 이름으로 유저 검색
    public List<User> findUserByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

    // 유저 전체 검색
    public List<User> findUserAll() {
         return userMapper.findAll();
    }

    // 유저 정보 업데이트
    public void updateUserByUserNumber(User user) {
         userMapper.updateUser(user);
    }
}
