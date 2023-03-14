package clothes.ecommerce.domain.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 사용자의 정보와 관련된 인터페이스
 * CRUD
 * C : createUser()
 * R : findByUserNumber(), findByUserName()
 * U : updateUser()
 * D : deleteAll(), deleteUser()
 */

@Mapper
@Repository
public interface UserMapper {
    void createUser(User user); // 회원가입
    Optional<User> findByUserNumber(Long userNumber); // 유저 번호로 검색
    List<User> findByUserName(String userName); // 유저 이름으로 검색
    List<User> findAll(); // 모든 유저 검색
    void updateUser(User user); //유저 정보 업데이트
    void deleteUser(Long userNumber); // 유저 번호로 유저 삭제
    void deleteAll(); // 모든 유저 삭제
}
