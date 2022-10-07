package africa.semicolon.lumexpress.data.dtos.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateProductResponse {
    private String message;
    private int statusCode;
    private String productName;
    private String description;
    private BigDecimal price;
}
