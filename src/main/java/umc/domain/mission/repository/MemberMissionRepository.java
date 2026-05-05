package umc.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.member.entity.Member;
import umc.domain.mission.entity.mapping.MemberMission;
import umc.domain.mission.enums.MissionStatus;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    Page<MemberMission> findAllByMemberAndStatus(Member member, MissionStatus status, PageRequest pageRequest);
    Integer countByMemberAndStatus(Member member, MissionStatus status); //특정 사용자의 특정 상태의 미션
}
