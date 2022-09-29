package africa.semicolon.lumexpress.data.dtos.request;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class CreateProductRequest {
    private String name;
    private BigDecimal price;
    @NotNull
    private MultipartFile imageUrl;
    private String productCategory;
    private int quantity;

}
