package umc.domain.point.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.point.entity.PointHistory;

public interface PointRepository extends JpaRepository<PointHistory, Long> {
}
