package clothes.ecommerce.domain.product;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface ProductMapper {
    void insert(Product product);
    Optional<Product> findByProductNumber(Long productNumber);
    List<Product> findByProductName(String productName);
    List<Product> findAll();
    void update(Product product);
    void delete(Long productNumber);
}
