package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dtos.request.CreateProductRequest;
import africa.semicolon.lumexpress.data.dtos.request.UpdateProductRequest;
import africa.semicolon.lumexpress.data.dtos.response.CreateProductResponse;
import africa.semicolon.lumexpress.data.models.Category;
import africa.semicolon.lumexpress.data.models.Product;
import africa.semicolon.lumexpress.data.repository.ProductRepository;
import africa.semicolon.lumexpress.exception.ProductNotFoundException;
import africa.semicolon.lumexpress.service.Cloud.CloudService;
import com.cloudinary.utils.ObjectUtils;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Getter
@Service
@Setter
@AllArgsConstructor
@Slf4j
@Builder
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final CloudService cloudService;
    @Override
    public CreateProductResponse create(CreateProductRequest createProductRequest) throws IOException {
        Product product = modelMapper.map(createProductRequest, Product.class);
        product.getCategory().add(Category.valueOf(createProductRequest.getProductCategory().toUpperCase()));
        var imageUrl = cloudService.upload(createProductRequest.getImageUrl().getBytes(), ObjectUtils.emptyMap());
        log.info("cloudinary image url::{}", imageUrl);
        product.setImageUrl(imageUrl);
        Product savedProduct = productRepository.save(product);
        return buildCreateProductResponse(savedProduct);

    }

    private CreateProductResponse buildCreateProductResponse(Product savedProduct) {
        return CreateProductResponse.builder()
                .code(201)
                .productId(savedProduct.getId())
                .message("Product saved successfully")
                .build();
    }

    @Override
    public String updateProductDetails(UpdateProductRequest updateProductRequest) {
        return null;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
//        Optional<Product> foundProduct = productRepository.findById(id);
//        if(foundProduct.isPresent()) return foundProduct.get();
//
//        throw new ProductNotFoundException(String.format("Product with is %d not found!", id));
          return productRepository.findById(id)
                  .orElseThrow(()->new
                          ProductNotFoundException(String.format("Product with id %d not found!", id)));
    }

    @Override
    public Page<Product> getAllProduct() {
        Pageable pageSpecs = PageRequest.of(0,5);
        productRepository.findAll(pageSpecs);
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
