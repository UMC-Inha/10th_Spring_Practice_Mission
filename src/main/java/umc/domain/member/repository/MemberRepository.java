package umc.domain.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.MemberMission;
import umc.domain.mission.entity.Mission;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // findById는 JPA가 기본 제공!

    Optional<Member> findByEmail(String email);
}