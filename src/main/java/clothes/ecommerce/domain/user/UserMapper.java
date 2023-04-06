package clothes.ecommerce.domain.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 사용자의 정보와 관련된 인터페이스
 * CRUD
 * C : createUser()
 * R : findByUserNumber(), findByUserName(), findByUserId()
 * U : updateUser()
 * D : deleteAll(), deleteUser()
 */

@Mapper
@Repository
public interface UserMapper {
    void createUser(User user); // 회원가입
    Optional<User> findByUserNumber(Long userNumber); // 유저 번호로 검색
    Optional<User> findByUserId(String userId); // 유저 아이디로 검색, 중복 아이디 검증에서 사용
    List<User> findAll(); // 모든 유저 검색
    void updateUser(User user); //유저 정보 업데이트
    void deleteUser(Long userNumber); // 유저 번호로 유저 삭제 but 비밀번호 필요
    Optional<User> login(User user); // 유저 로그인
}
