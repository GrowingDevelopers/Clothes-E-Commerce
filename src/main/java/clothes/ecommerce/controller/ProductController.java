package clothes.ecommerce.controller;

import clothes.ecommerce.domain.product.Product;
import clothes.ecommerce.domain.user.User;
import clothes.ecommerce.responseentity.Message;
import clothes.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Message> searchByProductNumber(@RequestBody Long productNumber) {
        Optional<Product> product = productService.findProductByProductNumber(productNumber);
        Message message = new Message();

        return getMessageResponseEntity(product, message);
    }

    /**
     * 상품 이름으로 상품 검색
     * @param productName
     * @return getMessageResponseEntity(product, message)
     */
    @GetMapping("/search/productName")
    public ResponseEntity<Message> searchByProductName(@RequestBody String productName) {
        List<Product> product = productService.findProductByProductName(productName);
        Message message = new Message();

        return getMessageResponseEntity(product, message);
    }

    /**
     * 전체 상품 검색
     * @return 전체 상품 리스트로 반환
     */
    @GetMapping("/search/product")
    public List<Product> searchProductAll() {
        return productService.findProductAll();
    }

    private ResponseEntity<Message> getMessageResponseEntity(Object object, Message message) {
        if(object instanceof Optional) {
            Optional<User> user = (Optional<User>) object;
            if(user.orElse(null) == null) {
                message.setMessage("존재하지 않는 데이터입니다.");
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }

            else {
                message.setMessage("검색에 성공하였습니다.");
                message.setData(user);
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
        }

        if(object instanceof List) {
            List<User> users = (List<User>) object;
            if(users.isEmpty()) {
                message.setMessage("존재하지 않는 데이터입니다.");
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }

            else {
                message.setMessage("검색에 성공하였습니다.");
                message.setData(users);
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
        }

        return null;
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
