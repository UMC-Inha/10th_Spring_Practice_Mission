package umc.domain.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.MemberMission;
import umc.domain.mission.entity.Mission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    // 내 미션 전체 조회
    Page<MemberMission> findAllByMember_Id(Long memberId, Pageable pageable);
}