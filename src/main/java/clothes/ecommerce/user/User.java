package clothes.ecommerce.user;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 사용자의 정보를 담은 클래스
 */

@Data
public class User {
    Long userNumber; // 유저 번호
    String userId; // 유저 아이디
    String userPassword; // 유저 패스워드
    String userName; // 유저의 이름
    String userAddress; // 유저의 주소
    String bankAccount; // 유저의 계좌
    BigDecimal bankAccountAmount; // 유저의 계좌 금액
    String userMemberShip = String.valueOf(UserMembership.BRONZE); // 유저의 회원등급
}
