package africa.semicolon.lumexpress.data.repository;

import africa.semicolon.lumexpress.data.models.Cart;
import africa.semicolon.lumexpress.data.models.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    void findByEmail() {
        var customer = Customer.builder()
                .cart(new Cart())
                .build();
        customer.setEmail("test@gmail.com");
        customer.setFirstName("ace");
        customer.setLastName("black");
        customer.setPassword("incorrect");
        var savedCustomer = customerRepository.save(customer);
        assertThat(savedCustomer).isNotNull();
        assertThat(customerRepository.findByEmail(savedCustomer.getEmail())).isNotNull();

    }
}