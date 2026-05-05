package umc.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
