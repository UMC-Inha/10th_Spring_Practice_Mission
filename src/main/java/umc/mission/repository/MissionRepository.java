package umc.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.location.enums.LocationName;
import umc.mission.entity.Mission;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query("SELECT m FROM Mission m " +
            "JOIN FETCH m.store s " +
            "WHERE s.location.name = :location " +
            "AND NOT EXISTS (SELECT mm FROM MemberMission mm WHERE mm.mission = m AND mm.member.id = :memberId)")
    Page<Mission> findAvailableMission(
            @Param("memberId") Long memberId,
            @Param("location") LocationName location,
            Pageable pageable
    );

    Page<Mission> findAllByStore_Id(Long storeId, Pageable pageable);
}
