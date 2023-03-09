package clothes.ecommerce.domain.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 사용자의 정보와 관련된 인터페이스
 */

@Mapper
@Repository
public interface UserMapper {
    void createUser(User user); // 회원가입
    void findByUserNumber(Long userNumber); // 유저 번호로 검색
    void findByUserName(String userName); // 유저 이름으로 검색
    void findAll(); // 모든 유저 검색
}
