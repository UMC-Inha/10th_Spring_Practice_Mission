package umc.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.mission.entity.Mission;

public interface MissionRespository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m WHERE m.store.region.name = :regionName " +
            "AND m.isActive = true " +
            "AND NOT EXISTS (SELECT mm FROM MemberMission mm WHERE mm.mission = m AND mm.member.id = :memberId)")
    Page<Mission> findAvailableMissionsByCity(
             String regionName, Long memberId, PageRequest pageRequest
    );
}
