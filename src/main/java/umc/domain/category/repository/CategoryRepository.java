package umc.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.category.entity.FoodCategory;

public interface CategoryRepository extends JpaRepository<FoodCategory, Long> {
}
