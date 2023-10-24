package clothes.ecommerce.controller;

import clothes.ecommerce.domain.product.Product;
import clothes.ecommerce.exception.CustomException;
import clothes.ecommerce.exception.ErrorCode;
import clothes.ecommerce.responseentity.MessageResponse;
import clothes.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    /**
     * 상품 등록
     * @param product
     * @return 상품 등록 완료
     */
    @PostMapping("/add/product")
    public String addProduct(@RequestBody Product product) {
        productService.insertProduct(product);
        return "상품 등록이 완료되었습니다.";
    }

    /**
     * 상품 번호로 상품 검색
     * @param productNumber
     * @return getMessageResponseEntity(product, message)
     */
    @GetMapping("/search/productNumber")
    public ResponseEntity<?> searchByProductNumber(@RequestBody Long productNumber) {
        Product product = productService.findProductByProductNumber(productNumber)
                .orElseThrow(() -> new CustomException(ErrorCode.DATA_NOT_FOUND));

        return MessageResponse.toFindResponseEntity(product);
    }

    /**
     * 상품 이름으로 상품 검색
     * @param productName
     * @return getMessageResponseEntity(product, message)
     */
    @GetMapping("/search/productName")
    public ResponseEntity<?> searchByProductName(@RequestBody String productName) {
        List<Product> products = productService.findProductByProductName(productName);

        if(products == null || products.isEmpty()) {
            throw new CustomException(ErrorCode.DATA_NOT_FOUND);
        }

        return MessageResponse.toFindResponseEntity(products);
    }

    /**
     * 전체 상품 검색
     * @return 전체 상품 리스트로 반환
     */
    @GetMapping("/search/product")
    public List<Product> searchProductAll() {
        return productService.findProductAll();
    }

    /**
     * 상품 정보 갱신
     * @param product
     * @return 상품 정보 갱신
     */
    @PostMapping("/update/product")
    public String update(@RequestBody Product product) {
        productService.updateProduct(product);
        return "상품 정보가 갱신되었습니다.";
    }

    /**
     * 상품 번호로 상품 삭제
     * @param productNumber
     * @return 상품 정보 삭제
     */
    @PostMapping("/delete/productNumber")
    public String delete(@RequestBody Long productNumber) {
        productService.deleteProduct(productNumber);
        return "상품 정보가 삭제되었습니다.";
    }

}
