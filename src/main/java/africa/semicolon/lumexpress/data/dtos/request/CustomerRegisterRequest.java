package africa.semicolon.lumexpress.data.dtos.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CustomerRegisterRequest {
    private String country;
    private String email;
    private String password;
}
