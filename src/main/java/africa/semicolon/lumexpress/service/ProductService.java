package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dtos.request.CreateProductRequest;
import africa.semicolon.lumexpress.data.dtos.request.UpdateProductRequest;
import africa.semicolon.lumexpress.data.dtos.response.CreateProductResponse;
import africa.semicolon.lumexpress.data.models.Product;
import africa.semicolon.lumexpress.exception.ProductNotFoundException;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    CreateProductResponse create (CreateProductRequest createProductRequest) throws IOException;
    String updateProductDetails(UpdateProductRequest updateProductRequest);
    Product getProductById(Long id) throws ProductNotFoundException;
    Page<Product> getAllProduct();
    void deleteProduct(Long id);
}
