package umc.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.location.entity.Location;
import umc.location.enums.LocationName;

public interface LocationRepository extends JpaRepository<Location, Long> {
    boolean existsByName(LocationName name);
}
