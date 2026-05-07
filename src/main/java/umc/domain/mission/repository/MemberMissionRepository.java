package umc.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umc.domain.mission.entity.mapping.MemberMission;
import umc.domain.mission.enums.MissionStatus;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("""
            select mm
            from MemberMission mm
            join fetch mm.mission m
            join fetch m.store s
            where mm.missionStatus in (:statuses)
                and mm.member.id = :memberId
                and (mm.dueDate > :cursorDueDate
                    or (mm.dueDate = :cursorDueDate and mm.id > :cursorId)
                )
            order by mm.dueDate asc, mm.id asc
            limit :pageSize
    """)
    List<MemberMission> findMemberMissionsUsingCursor(
            @Param("memberId") Long memberId,
            @Param("statuses") List<MissionStatus> statuses,
            @Param("cursorDueDate") LocalDate cursorDueDate,
            @Param("cursorId") Long cursorId,
            @Param("pageSize") Integer pageSize
    );

    @Query("""
            select count(mm)
            from MemberMission mm
            join mm.mission m
            join m.store s
            join s.region r
            where r.id = :regionId
                and mm.missionStatus = :status
                and mm.member.id = :memberId
    """)
    Integer countMissionsByRegion(
            @Param("memberId") Long memberId,
            @Param("regionId") Long regionId,
            @Param("status") MissionStatus status
    );
}
