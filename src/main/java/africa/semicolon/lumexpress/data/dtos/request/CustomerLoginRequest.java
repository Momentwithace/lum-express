package africa.semicolon.lumexpress.data.dtos.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerLoginRequest {
    private String email;
    private String password;
}
