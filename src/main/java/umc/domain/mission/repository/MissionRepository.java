package umc.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umc.domain.mission.entity.Mission;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
            select m
            from Mission m
                join fetch m.store s
                join s.region r
            where r.id = :regionId
                and m.dueDate >= current_date
                and not exists (
                    select 1
                    from MemberMission mm
                    where m.id = mm.mission.id
                        and mm.member.id = :memberId
                )
                and (
                    m.dueDate > :cursorDueDate
                        or (m.dueDate = :cursorDueDate and m.id > :cursorId)
                    )
            order by m.dueDate asc, m.id asc
            limit :pageSize
    """)
    List<Mission> findChallengeableMissionsByRegion(
            @Param("memberId") Long memberId,
            @Param("regionId") Long regionId,
            @Param("cursorDueDate") LocalDate cursorDueDate,
            @Param("cursorId") Long cursorId,
            @Param("pageSize") int pageSize
    );
}
