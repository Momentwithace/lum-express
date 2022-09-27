package africa.semicolon.lumexpress.data.repository;

import africa.semicolon.lumexpress.data.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
