package umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
