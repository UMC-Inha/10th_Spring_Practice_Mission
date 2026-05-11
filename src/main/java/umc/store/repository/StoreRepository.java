package umc.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.store.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long>  {
}
