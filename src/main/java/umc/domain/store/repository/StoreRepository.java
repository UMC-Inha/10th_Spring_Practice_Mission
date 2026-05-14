package umc.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.domain.store.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}
