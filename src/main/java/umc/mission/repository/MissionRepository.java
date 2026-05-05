package umc.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.mission.entity.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
