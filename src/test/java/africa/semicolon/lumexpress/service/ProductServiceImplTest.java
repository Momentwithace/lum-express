package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dtos.request.CreateProductRequest;
import africa.semicolon.lumexpress.data.dtos.response.CreateProductResponse;
import africa.semicolon.lumexpress.data.models.Product;
import africa.semicolon.lumexpress.exception.ProductNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
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
                .imageUrl(file)
                .build();
    }

    @Test
    void createTest() throws IOException {
       CreateProductResponse createProductResponse = productService.create(createProductRequest);
       assertThat(createProductResponse).isNotNull();
       assertThat(createProductResponse.getProductId()).isGreaterThan(0L);
       assertThat(createProductResponse.getMessage()).isNotNull();
       assertThat(createProductResponse.getCode()).isEqualTo(201);
    }

    @Test
    void updateProductDetailsTest() {
    }

    @Test
    void getProductByIdTest() throws IOException, ProductNotFoundException {
        CreateProductResponse createProductResponse = productService.create(createProductRequest);
        Product foundProduct = productService.getProductById(createProductResponse.getProductId());
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getId()).isEqualTo(createProductResponse.getProductId());

    }

    @Test
    void getAllProductTest() {
        Page<Product> productsPage = productService.getAllProduct();
        assertThat(productsPage).isNotNull();
        assertThat(productsPage.getTotalElements()).isGreaterThan(0);
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void builder() {
    }
}