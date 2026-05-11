package umc.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.domain.store.entity.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
	boolean existsByName(String name);
}
