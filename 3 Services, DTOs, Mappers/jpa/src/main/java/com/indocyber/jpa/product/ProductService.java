package com.indocyber.jpa.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> storedProcedure(String categoryName) {
        return productRepository.getProductsByCategory(categoryName);
    }

}
