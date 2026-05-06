package umc.domain.region.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.domain.region.entity.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}