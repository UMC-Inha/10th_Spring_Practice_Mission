package umc.domain.inquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.inquiry.entity.Inquiry;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}
