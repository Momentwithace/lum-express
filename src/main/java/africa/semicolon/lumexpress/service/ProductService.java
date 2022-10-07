package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dtos.request.CreateProductRequest;
import africa.semicolon.lumexpress.data.dtos.request.GetAllItemsRequest;
import africa.semicolon.lumexpress.data.dtos.request.UpdateProductRequest;
import africa.semicolon.lumexpress.data.dtos.response.CreateProductResponse;
import africa.semicolon.lumexpress.data.dtos.response.UpdateProductResponse;
import africa.semicolon.lumexpress.data.models.Product;
import africa.semicolon.lumexpress.exception.ProductNotFoundException;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    CreateProductResponse create (CreateProductRequest createProductRequest) throws IOException;
    UpdateProductResponse updateProductDetails(Long productId, JsonPatch patch) throws ProductNotFoundException;
    Product getProductById(Long id) throws ProductNotFoundException;
    Page<Product> getAllProduct(GetAllItemsRequest getAllItemsRequest);

    void deleteProduct(Long id);
}
