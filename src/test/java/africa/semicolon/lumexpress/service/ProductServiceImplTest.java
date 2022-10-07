package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dtos.request.CreateProductRequest;
import africa.semicolon.lumexpress.data.dtos.request.GetAllItemsRequest;
import africa.semicolon.lumexpress.data.dtos.request.UpdateProductRequest;
import africa.semicolon.lumexpress.data.dtos.response.CreateProductResponse;
import africa.semicolon.lumexpress.data.dtos.response.UpdateProductResponse;
import africa.semicolon.lumexpress.data.models.Product;
import africa.semicolon.lumexpress.exception.ProductNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.ReplaceOperation;
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
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    private ProductService productService;

    private CreateProductRequest createProductRequest;
    private CreateProductResponse createProductResponse;

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
        createProductResponse = productService.create(createProductRequest);
    }

    @Test
    void createTest() throws IOException {
//       CreateProductResponse createProductResponse = productService.create(createProductRequest);
       assertThat(createProductResponse).isNotNull();
       assertThat(createProductResponse.getProductId()).isGreaterThan(0L);
       assertThat(createProductResponse.getMessage()).isNotNull();
       assertThat(createProductResponse.getCode()).isEqualTo(201);
    }

    @Test
    void updateProductDetailsTest() {
        ObjectMapper mapper = new ObjectMapper();
        UpdateProductResponse updateProductResponse = null;


        try {
            JsonNode value = mapper.readTree("\"egg\"");
            JsonPatch patch = new JsonPatch((List.of(new ReplaceOperation(new JsonPointer("/name"), value))));
            updateProductResponse = productService.updateProductDetails(1L, patch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(updateProductResponse).isNotNull();
        assertThat(updateProductResponse.getStatusCode()).isEqualTo(200);
        assertThat(productService.getProductById(1L).getName()).isEqualTo("egg");

    }

    private UpdateProductRequest buildUpdateRequest(){
        return UpdateProductRequest.builder()
                .price(BigDecimal.valueOf(40.00))
                .productId(1L)
                .description("its Just milo")
                .quantity(10)
                .build();
    }

    @Test
    void getProductByIdTest() throws IOException, ProductNotFoundException {
        CreateProductResponse createProductResponse = productService.create(createProductRequest);
        Product foundProduct = productService.getProductById(createProductResponse.getProductId());
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getId()).isEqualTo(createProductResponse.getProductId());

    }

    @Test
    void getAllProductTest() throws IOException {
        productService.create(createProductRequest);
        var getItemsRequest = buildGetItemsRequest();
        Page<Product> productsPage = productService.getAllProduct(getItemsRequest);
        assertThat(productsPage).isNotNull();
        assertThat(productsPage.getTotalElements()).isGreaterThan(0);
    }

    private GetAllItemsRequest buildGetItemsRequest() {
        return GetAllItemsRequest.builder()
                .numberOfItemsPerPage(8)
                .pageNumber(1)
                .build();
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void builder() {
    }
}