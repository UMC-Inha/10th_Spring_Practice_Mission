package umc.term.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.term.entity.Term;
import umc.term.enums.TermName;

import java.util.List;

public interface TermRepository extends JpaRepository<Term, Long> {
    List<Term> findAllByNameIn(List<TermName> names);
}
