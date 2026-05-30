package umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.domain.member.entity.Term;

import java.util.List;

public interface TermRepository extends JpaRepository<Term, Long> {
    List<Term> findAllByIsRequired(boolean isRequired);

    @Query("""
        select t.id
            from Term t
    """)
    List<Long> findAllIds();
}
