package umc.domain.term.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.domain.term.entity.Term;

public interface TermRepository extends JpaRepository<Term, Long> {
}