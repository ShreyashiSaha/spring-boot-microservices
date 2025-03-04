package com.shreyashi.productservice.service;

import com.shreyashi.productservice.ProductRepository;
import com.shreyashi.productservice.dto.ProductRequest;
import com.shreyashi.productservice.dto.ProductResponse;
import com.shreyashi.productservice.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder().name(productRequest.getName()).description(productRequest.getDescription()).price(productRequest.getPrice()).build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::maptoProductResponse).collect(Collectors.toList());
    }

    private ProductResponse maptoProductResponse(Product product) {
        return ProductResponse.builder().id(product.getId()).name(product.getDescription()).price(product.getPrice()).build();
    }
}
