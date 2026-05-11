package umc.domain.Store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.Store.entity.Store;

public interface StoreRepository extends JpaRepository<Store,Long> {
}
