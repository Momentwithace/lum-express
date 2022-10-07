package africa.semicolon.lumexpress.data.dtos.request;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
public class UpdateProductRequest {
    private Long productId;
    private BigDecimal price;
    private int quantity;
    private String description;
    private int statusCode;
}
