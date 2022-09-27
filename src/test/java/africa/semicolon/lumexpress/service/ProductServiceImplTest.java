package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dtos.request.CreateProductRequest;
import africa.semicolon.lumexpress.data.dtos.response.CreateProductResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    private ProductService productService;

    private CreateProductRequest createProductRequest;

    @BeforeEach
    void setUp() throws IOException {
        Path path = Paths.get("src/main/resources/Img/milk.png");
        MultipartFile file = new MockMultipartFile("milk", Files.readAllBytes(path));
        createProductRequest = CreateProductRequest.builder()
                .name("Milk")
                .price(BigDecimal.valueOf(30.00))
                .productCategory("Beverages")
                .quantity(10)
                .build();
    }

    @Test
    void create() {
       CreateProductResponse createProductResponse = productService.create(createProductRequest);
       assertThat(createProductResponse).isNotNull();
       assertThat(createProductResponse.getProductId()).isGreaterThan(0L);
       assertThat(createProductResponse.getMessage()).isNotNull();
       assertThat(createProductResponse.getCode()).isEqualTo(201);
    }

    @Test
    void updateProductDetails() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void getAllProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void builder() {
    }
}