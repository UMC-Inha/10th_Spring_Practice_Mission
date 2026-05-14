package umc.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.mission.entity.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
            SELECT m FROM Mission m
            JOIN m.store s
            JOIN s.region r
            WHERE r.name = :regionName
            AND m.id NOT IN (
                SELECT mm.mission.id FROM MemberMission mm WHERE mm.member.id = :memberId
            )
            ORDER BY m.deadLine ASC
            """)
    Page<Mission> findMissionsByRegion(
            @Param("memberId") Long memberId,
            @Param("regionName") String regionName,
            Pageable pageable
    );


}
