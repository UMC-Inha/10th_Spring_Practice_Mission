package umc.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.category.entity.FoodCategory;
import umc.domain.category.enums.CategoryName;

import java.util.List;

public interface CategoryRepository extends JpaRepository<FoodCategory, Long> {

    List<FoodCategory> findAllByNameIn(List<CategoryName> categoryNameList);
}
