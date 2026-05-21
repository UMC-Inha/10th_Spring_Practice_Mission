package umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.member.entity.mapping.TermAgreement;

public interface TermAgreementRepository extends JpaRepository<TermAgreement, Long> {
}
