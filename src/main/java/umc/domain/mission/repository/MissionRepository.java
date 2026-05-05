package umc.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.member.entity.Member;
import umc.domain.mission.entity.Mission;
import umc.domain.store.entity.Region;
import umc.domain.store.entity.Store;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission,Long> {
    @Query("SELECT COUNT(m) FROM Mission m WHERE m.store.region = :region")
    Integer countByRegion(@Param("region") Region region);

    @Query("SELECT m FROM Mission m WHERE m.store.region = :region AND m.isActive = false")
    List<Mission> findUnstartedMissions(@Param("region") Region region);
}
