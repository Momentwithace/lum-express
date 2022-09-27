package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dtos.request.CreateProductRequest;
import africa.semicolon.lumexpress.data.dtos.request.UpdateProductRequest;
import africa.semicolon.lumexpress.data.dtos.response.CreateProductResponse;
import africa.semicolon.lumexpress.data.models.Product;

import java.util.List;

public interface ProductService {
    CreateProductResponse create (CreateProductRequest createProductRequest);
    String updateProductDetails(UpdateProductRequest updateProductRequest);
    Product getProductById(Long id);
    List<Product> getAllProduct();
    void deleteProduct(Long id);
}
