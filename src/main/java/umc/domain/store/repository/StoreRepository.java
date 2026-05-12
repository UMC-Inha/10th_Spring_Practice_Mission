package umc.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.store.entity.Region;
import umc.domain.store.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
