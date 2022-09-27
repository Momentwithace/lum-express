package africa.semicolon.lumexpress.data.repository;

import africa.semicolon.lumexpress.data.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
