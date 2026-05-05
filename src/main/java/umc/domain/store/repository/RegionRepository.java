package umc.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import umc.domain.store.entity.Region;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByName(String name);
}
