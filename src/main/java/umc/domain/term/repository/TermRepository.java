package umc.domain.term.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.term.entity.Term;
import umc.domain.term.enums.TermName;

import java.util.List;

public interface TermRepository extends JpaRepository<Term, Long> {

    List<Term> findAllByNameIn(List<TermName> termNameList);
}
