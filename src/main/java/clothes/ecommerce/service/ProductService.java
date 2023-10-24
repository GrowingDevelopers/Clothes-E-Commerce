package clothes.ecommerce.service;

import clothes.ecommerce.domain.product.Product;
import clothes.ecommerce.domain.product.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;

    public void insertProduct(Product product) {
        productMapper.insert(product);
    }

    public Optional<Product> findProductByProductNumber(Long productNumber) {
        return productMapper.findByProductNumber(productNumber);
    }

    public List<Product> findProductByProductName(String productName) {
        return productMapper.findByProductName(productName);
    }

    public List<Product> findProductAll() {
        return productMapper.findAll();
    }

    public void updateProduct(Product product) {
        productMapper.update(product);
    }

    public void deleteProduct(Long productNumber) {
        productMapper.delete(productNumber);
    }
}
