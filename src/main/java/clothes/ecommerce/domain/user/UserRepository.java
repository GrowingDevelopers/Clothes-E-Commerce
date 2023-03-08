package clothes.ecommerce.domain.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 사용자의 정보와 관련된 인터페이스
 */

@Mapper
@Repository
public interface UserRepository {
    void save(User user);
    void findByUserNumber(Long userNumber);
    void findByUserName(String userName);
}
