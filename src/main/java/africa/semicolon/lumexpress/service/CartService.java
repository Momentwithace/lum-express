package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dtos.request.CartRequest;
import africa.semicolon.lumexpress.data.dtos.response.CartResponse;
import africa.semicolon.lumexpress.data.models.Cart;

import java.util.List;

public interface CartService {
    CartResponse addProductToCart(CartRequest cartRequest);
    List<Cart> getCartList();
}
