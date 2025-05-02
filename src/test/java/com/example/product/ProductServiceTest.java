package com.example.product;

import com.example.product.dto.ProductDto;
import com.example.product.entity.Product;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

class ProductServiceTest {
    // 단위테스트 (Unit Test) : 보통 목 객체를 사용하여 외부 의존성을 제거하고, 테스트 대상 코드만을 검증

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    String productId = "999999999";
    private ProductDto dto;
    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        product = Product.builder()
                .productId("999999999")
                .productName("na,me")
                .productPrice(1000L)
                .description("description")
                .storeId("0")
                .build();

        dto = new ProductDto("999999999","na,me", 1000L,"description","0");
    }

    @Test
    void createProduct_단위테스트() {
        // Given
        // When
        when(productRepository.save(any(Product.class))).thenReturn(product);
        Product createdProduct = productService.createProduct(dto);

        // Then
        assertNotNull(createdProduct);
        assertThat(createdProduct.getProductId()).isEqualTo(dto.getProductId());
        assertThat(createdProduct.getProductName()).isEqualTo(dto.getProductName());
        assertThat(createdProduct.getProductPrice()).isEqualTo(dto.getProductPrice());
        assertThat(createdProduct.getDescription()).isEqualTo(dto.getDescription());
        assertThat(createdProduct.getStoreId()).isEqualTo(dto.getStoreId());

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void getProduct_단위테스트() {
        // Given

        // When
        when(productRepository.findByProductId(productId)).thenReturn(java.util.Optional.of(product));
        Product foundProduct = productService.getProduct(productId);

        // Then
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getProductId()).isEqualTo(dto.getProductId());
        assertThat(foundProduct.getProductName()).isEqualTo(dto.getProductName());
        assertThat(foundProduct.getProductPrice()).isEqualTo(dto.getProductPrice());
        assertThat(foundProduct.getDescription()).isEqualTo(dto.getDescription());
        assertThat(foundProduct.getStoreId()).isEqualTo(dto.getStoreId());

        verify(productRepository, times(1)).findByProductId(productId);
    }

    @Test
    void updateProduct_단위테스트() {
        // Given
        Product updatedProduct = Product.builder()
                .productId(productId)
                .productName(dto.getProductName())
                .productPrice(dto.getProductPrice())
                .build();

        // When
        when(productRepository.findByProductId(productId)).thenReturn(java.util.Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);
        Product result = productService.updateProduct(productId, dto);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getProductName()).isEqualTo(dto.getProductName());
        assertThat(result.getProductPrice()).isEqualTo(dto.getProductPrice());

        verify(productRepository, times(1)).findByProductId(productId);
        verify(productRepository, times(1)).save(any(Product.class));
    }
    
    @Test
    void deleteProduct_단위테스트() {
        // When
        when(productRepository.findByProductId(productId)).thenReturn(java.util.Optional.of(product));
        productService.deleteProduct(productId);

        // Then
        verify(productRepository, times(1)).findByProductId(productId);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void deleteProduct_상품없음_단위테스트() {
        // Given
        when(productRepository.findByProductId("555")).thenReturn(Optional.empty());

        // When
        assertThatThrownBy(() -> productService.deleteProduct("555"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 상품이 존재하지 않습니다.");

        // Then
        verify(productRepository, times(1)).findByProductId("555");
    }


}
