package umc.domain.region.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.region.entity.City;


import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Long> {
    Optional<City> findByName(String name);
}
