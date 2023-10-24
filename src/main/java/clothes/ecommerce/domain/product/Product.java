package clothes.ecommerce.domain.product;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 상품의 정보를 담은 클래스
 */

@Data
public class Product {
    private Long productNumber; // 상품 번호
    private BigDecimal price; // 상품 가격
    private Long productStock; // 상품의 재고 수량
    private String productName; // 상품 명
    private String size; // 상품의 사이즈
    private String color; // 상품의 컬러
    private Long productSellAmount; // 상품의 판매 수량
}
