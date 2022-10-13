package africa.semicolon.lumexpress.data.dtos.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    private String message;
    private Long userId;
    private int code;
}
