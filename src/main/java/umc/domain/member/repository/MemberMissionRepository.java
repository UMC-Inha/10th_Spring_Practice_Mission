package umc.domain.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.mission.enums.MissionStatus;

import java.time.LocalDate;
import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("""
            SELECT mm FROM MemberMission mm
            JOIN FETCH mm.mission m
            JOIN FETCH m.store s
            WHERE mm.member.id = :memberId AND mm.missionStatus = :status
            ORDER BY mm.startedAt DESC
            """)
    Page<MemberMission> findByMemberIdAndStatus(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );

    @Query("""
            SELECT COUNT(mm) FROM MemberMission mm
            JOIN mm.mission m
            JOIN m.store s
            JOIN s.region r
            WHERE mm.member.id = :memberId
            AND mm.missionStatus = :status
            AND r.name = :regionName
            """)
    Integer countCompletedMissionsByRegion(
            @Param("memberId") Long memberId,
            @Param("regionName") String regionName,
            @Param("status") MissionStatus status
    );


}
