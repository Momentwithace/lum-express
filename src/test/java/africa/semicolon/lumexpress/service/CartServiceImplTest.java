package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dtos.request.CartRequest;
import africa.semicolon.lumexpress.data.dtos.request.GetAllItemsRequest;
import africa.semicolon.lumexpress.data.dtos.response.CartResponse;
import africa.semicolon.lumexpress.data.models.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CartServiceImplTest {

    @Autowired
    private CartService cartService;
    private ProductService  productService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void addProductToCartTest(){
        CartRequest cartRequest = CartRequest.builder()
                .cartId(cartService.getCartList()
                        .get(cartService.getCartList().size()-1)
                        .getId())
                .productId(productService.getAllProduct(new GetAllItemsRequest(10, 1))
                        .getContent().get(productService.getAllProduct(new GetAllItemsRequest ())
                                        .getNumberOfElements()-1).getId())
                .build();
        CartResponse cartResponse = cartService.addProductToCart(cartRequest);
        assertThat(cartResponse).isNotNull();
    }
}