package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dtos.request.CustomerRegisterRequest;
import africa.semicolon.lumexpress.data.dtos.request.UpdateCustomerDetails;
import africa.semicolon.lumexpress.data.dtos.response.RegisterResponse;
import africa.semicolon.lumexpress.exception.LumExpressException;
import africa.semicolon.lumexpress.utils.LumExpressUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CustomerServiceImplTest {
    @Autowired
    private CustomerService customerService;

    private CustomerRegisterRequest registerRequest;


    @BeforeEach
    void setUp() {
        registerRequest = CustomerRegisterRequest.builder()
                .email("test@gmail.com")
                .country("sweden")
                .password("1234")
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void register() throws LumExpressException {
        RegisterResponse customerRegisterResponse = customerService.register(registerRequest);
        assertThat(customerRegisterResponse).isNotNull();
        assertThat(customerRegisterResponse.getMessage()).isNotNull();
        assertThat(customerRegisterResponse.getUserId()).isGreaterThan(0);
        assertThat(customerRegisterResponse.getCode()).isEqualTo(201);
    }



    @Test
    void updateProfile() throws LumExpressException {
        RegisterResponse registerResponse = customerService.register(registerRequest);
        UpdateCustomerDetails details = UpdateCustomerDetails.builder()
                .customerId(registerResponse.getUserId())
                .imageUrl(LumExpressUtils.getMockCloudinaryImageUrl())
                .lastName("unlikeace")
                .phoneNumber("09087")
                .build();
        var updateResponse = customerService.updateCustomerProfile(details);
        assertThat(updateResponse).isNotNull();
        assertThat(updateResponse.contains("success")).isTrue();
    }
}