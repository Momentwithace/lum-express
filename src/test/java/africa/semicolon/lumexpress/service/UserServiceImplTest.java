package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dtos.request.LoginRequest;
import africa.semicolon.lumexpress.data.models.Customer;
import africa.semicolon.lumexpress.data.repository.CustomerRepository;
import com.sun.jdi.connect.spi.ClosedConnectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerRepository customerRepository;

    private LoginRequest loginRequest;

    @BeforeEach
    void setUp() {

        loginRequest = LoginRequest.builder()
                .email("test@gmail.com")
                .password("incorrect")
                .build();


    }

    @Test
    void login() {
        var response = userService.login(loginRequest);
        assertThat(response).isNotNull();
        assertThat(response.getCode()).isEqualTo(200);

    }
}