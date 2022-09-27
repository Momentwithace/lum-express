package africa.semicolon.lumexpress.data.dtos.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class CreateProductRequest {
    private String name;
    private BigDecimal price;
    private MultipartFile imageUrl;
    private String productCategory;
    private int quantity;

}
