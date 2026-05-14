package umc.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.store.entity.Review;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>{
    // member_id와 store_id로 리뷰 조회
    List<Review> findByMemberIdAndStoreId(Long memberId, Long storeId);
}

