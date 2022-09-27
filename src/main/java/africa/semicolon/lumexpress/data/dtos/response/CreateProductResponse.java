package africa.semicolon.lumexpress.data.dtos.response;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductResponse {
    private String message;
    private Long productId;
    private int code;
}
