package umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.domain.member.entity.Term;

@Repository
public interface TermRepository extends JpaRepository<Term, Long> {
}
