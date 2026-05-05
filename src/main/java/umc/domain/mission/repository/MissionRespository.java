package umc.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.mission.entity.Mission;

public interface MissionRespository extends JpaRepository<Mission, Long> {
}
