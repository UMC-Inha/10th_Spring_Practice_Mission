package umc.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.mission.entity.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    Page<Mission> findAllByStoreId(Long storeId, Pageable pageable);
}