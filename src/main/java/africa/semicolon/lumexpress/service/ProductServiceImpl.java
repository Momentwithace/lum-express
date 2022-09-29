package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dtos.request.CreateProductRequest;
import africa.semicolon.lumexpress.data.dtos.request.UpdateProductRequest;
import africa.semicolon.lumexpress.data.dtos.response.CreateProductResponse;
import africa.semicolon.lumexpress.data.models.Category;
import africa.semicolon.lumexpress.data.models.Product;
import africa.semicolon.lumexpress.data.repository.ProductRepository;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Getter
@Service
@Setter
@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    @Override
    public CreateProductResponse create(CreateProductRequest createProductRequest) {
        Product product = modelMapper.map(createProductRequest, Product.class);
        product.getCategory().add(Category.valueOf(createProductRequest.getProductCategory()));

        return null;
    }

    @Override
    public String updateProductDetails(UpdateProductRequest updateProductRequest) {
        return null;
    }

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
