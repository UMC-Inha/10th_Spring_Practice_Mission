package umc.domain.mission.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.mission.entity.mapping.MemberMission;
import umc.domain.mission.enums.MemberMissionStatus;


import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query(value = """
            select mm
            from MemberMission mm
            join fetch mm.mission m
            join fetch m.store s
            where mm.member.id = :memberId
                and mm.status in :statuses""",
        countQuery = """
            select count(mm)
            from MemberMission mm
            where mm.member.id = :memberId
                and mm.status in :statuses"""
    )
    Page<MemberMission> findMyMissions(
            @Param("memberId") Long memberId,
            @Param("statuses") List<MemberMissionStatus> statuses,
            Pageable pageable
            );

    int countByMemberIdAndStatus(Long memberId, MemberMissionStatus status);
}
