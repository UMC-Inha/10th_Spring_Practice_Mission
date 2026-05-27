package umc.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.member.entity.mapping.MemberFood;

public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {
}
