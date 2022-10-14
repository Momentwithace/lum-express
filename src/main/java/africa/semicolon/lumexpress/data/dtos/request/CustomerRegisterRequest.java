package africa.semicolon.lumexpress.data.dtos.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CustomerRegisterRequest {
    @NotEmpty(message = "You must pick a country")
    @NotNull(message = "Cannot be null")
    private String country;
    @NotNull(message = "Email cannot be not")
    @Email(message = "Invalid Email")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @NotNull(message = "Field Cannot be null")
    @NotEmpty(message = "Field cannot be empty")
    private String firstName;
    @NotEmpty(message = "Password cannot be empty")
    @NotNull(message = "Cannot be null")
    private String password;
}
