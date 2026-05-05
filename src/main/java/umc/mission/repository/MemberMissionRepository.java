package umc.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.mission.entity.Mapping.MemberMission;
import umc.mission.enums.MissionStatus;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    @Query("SELECT mm FROM MemberMission mm WHERE mm.member.id = :memberId AND mm.isComplete = :isComplete")
    Page<MemberMission> findMyMissions(
            @Param("memberId") Long memberId,
            @Param("isComplete") MissionStatus isComplete,
            Pageable pageable
    );
}
