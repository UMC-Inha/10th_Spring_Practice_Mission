package umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.domain.member.entity.Food;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    @Query("""
        select f.id
            from Food f
    """)
    List<Long> findAllIds();
}
