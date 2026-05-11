package umc.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
