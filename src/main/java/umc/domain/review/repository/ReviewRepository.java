package umc.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.Store.entity.Store;
import umc.domain.review.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByStore(Store store);
}
