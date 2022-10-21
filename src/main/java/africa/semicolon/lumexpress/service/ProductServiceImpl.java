package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dtos.request.CreateProductRequest;
import africa.semicolon.lumexpress.data.dtos.request.GetAllItemsRequest;
import africa.semicolon.lumexpress.data.dtos.response.CreateProductResponse;
import africa.semicolon.lumexpress.data.dtos.response.UpdateProductResponse;
import africa.semicolon.lumexpress.data.models.Category;
import africa.semicolon.lumexpress.data.models.Product;
import africa.semicolon.lumexpress.data.repository.ProductRepository;
import africa.semicolon.lumexpress.exception.ProductNotFoundException;
import africa.semicolon.lumexpress.service.Cloud.CloudService;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

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
    private ObjectMapper objectMapper;
    @Override
    public CreateProductResponse create(CreateProductRequest createProductRequest) throws IOException {
        Product product = modelMapper.map(createProductRequest, Product.class);
        product.getCategory().add(Category.valueOf(createProductRequest.getProductCategory().toUpperCase()));
        var imageUrl = cloudService.upload(createProductRequest.getImageUrl().getBytes(), ObjectUtils.emptyMap());
        log.info("cloudinary image url::{}", imageUrl);
        product.setImageUrl(imageUrl);
        var savedProduct = productRepository.save(product);
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
    public UpdateProductResponse updateProductDetails(Long productId, JsonPatch patch) {
        var foundProduct = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);

        Product updatedProduct = applyPatchToProduct(patch, foundProduct);
        assert updatedProduct != null;
        var savedProduct = productRepository.save(updatedProduct);
        return buildUpdateResponse(savedProduct);
    }

    private Product applyPatchToProduct(JsonPatch patch, Product foundProduct) {
        var productNode = objectMapper.convertValue(foundProduct, JsonNode.class);
        try{
            var patchedProductNode = patch.apply(productNode);
            return objectMapper.readValue(objectMapper.writeValueAsBytes(patchedProductNode), Product.class);
        }catch (IOException| JsonPatchException exception){
            throw new RuntimeException(exception.getMessage(), exception.getCause());
        }
    }

    private UpdateProductResponse buildUpdateResponse(Product savedProduct) {
        return UpdateProductResponse.builder()
                .productName(savedProduct.getName())
                .price(savedProduct.getPrice())
                .message("Update Successfully")
                .statusCode(200)
                .build();
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
    public Page<Product> getAllProduct(GetAllItemsRequest getAllItemsRequest) {
        Pageable pageSpecs = PageRequest.of(getAllItemsRequest.getPageNumber()-1, getAllItemsRequest.getNumberOfItemsPerPage());
        Page<Product> products = productRepository.findAll(pageSpecs);
        return products;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
