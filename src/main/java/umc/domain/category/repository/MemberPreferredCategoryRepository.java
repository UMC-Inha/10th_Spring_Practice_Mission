package umc.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.category.entity.mapping.MemberPreferredCategory;

public interface MemberPreferredCategoryRepository extends JpaRepository<MemberPreferredCategory, Long> {
}
