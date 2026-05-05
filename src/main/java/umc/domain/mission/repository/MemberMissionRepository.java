package umc.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.member.entity.Member;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.entity.mapping.MemberMission;
import umc.domain.store.entity.Region;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission,Integer> {
    List<MemberMission> findAllByMemberAndIsCompleted(Member member, boolean isCompleted);

    @Query("SELECT COUNT(mm) FROM MemberMission mm WHERE mm.member.id=:memberId AND " +
            "mm.mission.store.region = :region AND mm.isCompleted = true")
    Integer countCompletedMissionsByRegion(@Param("memberId") Long memberId, @Param("region") Region region);
}
