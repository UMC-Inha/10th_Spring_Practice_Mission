package umc.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String username);
}
