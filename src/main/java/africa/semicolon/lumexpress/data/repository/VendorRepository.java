package africa.semicolon.lumexpress.data.repository;

import africa.semicolon.lumexpress.data.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Vendor, Long> {
}
