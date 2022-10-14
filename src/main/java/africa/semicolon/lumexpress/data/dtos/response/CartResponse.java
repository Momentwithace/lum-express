package africa.semicolon.lumexpress.data.dtos.response;

import africa.semicolon.lumexpress.data.models.Cart;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    private String message;
    private Cart cart;
}
