package africa.semicolon.lumexpress.data.dtos.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerDetails {
    private String lastName;
    private Long customerId;
    private String phoneNumber;
    private String imageUrl;
    private int buildingNumber;
    private String street;
    private String city;
    private String state;
}
