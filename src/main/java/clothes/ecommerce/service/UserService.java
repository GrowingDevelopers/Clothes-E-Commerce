package clothes.ecommerce.service;

import clothes.ecommerce.domain.user.User;
import clothes.ecommerce.domain.user.UserMapper;
import clothes.ecommerce.message.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
     public boolean joinUser(User user) {
         try {
             validateDuplicateUserId(user);
             userMapper.createUser(user);
             return true;
         } catch(IllegalStateException e) {
            return false;
         }
     }
     // 중복 아이디 검증
     private void validateDuplicateUserId(User user) {
        userMapper.findByUserId(user.getUserId())
                .ifPresent(u -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });
     }

     // 유저 번호로 유저 검색
    public Optional<User> findUserByUserNumber(Long userNumber) {
         return userMapper.findByUserNumber(userNumber);
    }
    // 유저 이름으로 유저 검색
    public List<User> findUserByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }
    // 유저 아이디로 유저 검색
    public Optional<User> findUserByUserId(String userId) {
         return userMapper.findByUserId(userId);
    }

    // 유저 전체 검색
    public List<User> findUserAll() {
         return userMapper.findAll();
    }

    // 유저 정보 업데이트
    public void updateUserByUserNumber(User user) {
         userMapper.updateUser(user);
    }

    // 유저 정보 삭제
    public void deleteUserByUserNumber(Long userNumber) {
         userMapper.deleteUser(userNumber);
    }
}
