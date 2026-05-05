package umc.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.food.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
