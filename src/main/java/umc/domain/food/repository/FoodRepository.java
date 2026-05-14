package umc.domain.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.domain.food.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
}