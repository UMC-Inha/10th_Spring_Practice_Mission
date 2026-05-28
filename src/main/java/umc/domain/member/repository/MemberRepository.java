package umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);
}
