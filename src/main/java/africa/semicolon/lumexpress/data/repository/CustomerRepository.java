package africa.semicolon.lumexpress.data.repository;

import africa.semicolon.lumexpress.data.models.Customer;
import africa.semicolon.lumexpress.data.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}
