package clothes.ecommerce.domain.user;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 사용자의 정보를 담은 클래스
 */

@Data
public class User {
    private Long userNumber; // 유저 번호
    private String userId; // 유저 아이디
    private String userPassword; // 유저 패스워드
    private String userName; // 유저의 이름
    private String userAddress; // 유저의 주소
    private String bankAccount; // 유저의 계좌
    private BigDecimal bankAccountAmount; // 유저의 계좌 금액
    private String userMemberShip = String.valueOf(UserMembership.BRONZE); // 유저의 회원등급
}
