package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.models.Cart;
import africa.semicolon.lumexpress.data.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;

    @Override
    public Cart create() {
      return cartRepository.save(new Cart());

    }
}
