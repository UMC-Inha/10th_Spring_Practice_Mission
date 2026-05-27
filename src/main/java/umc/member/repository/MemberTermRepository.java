package umc.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.member.entity.mapping.MemberTerm;

public interface MemberTermRepository extends JpaRepository<MemberTerm, Long> {
}
