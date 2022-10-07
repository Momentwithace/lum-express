package africa.semicolon.lumexpress.data.repository;

import africa.semicolon.lumexpress.data.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
