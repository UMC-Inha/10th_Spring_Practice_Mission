package umc.term.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.term.entity.Term;

public interface TermRepository extends JpaRepository<Term, Long> {
}
