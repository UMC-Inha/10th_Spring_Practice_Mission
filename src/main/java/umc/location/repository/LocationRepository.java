package umc.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.location.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
