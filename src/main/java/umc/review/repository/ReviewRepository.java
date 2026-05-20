package umc.review.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.member.entity.Member;
import umc.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ID순
    Slice<Review> findAllByMemberIdOrderByIdDesc(Long memberId, Pageable pageable);
    Slice<Review> findAllByMemberIdAndIdLessThanOrderByIdDesc(Long memberId, Long id, Pageable pageable);


    // 별점순
    Slice<Review> findAllByMemberIdOrderByStarDescIdDesc(Long memberId, Pageable pageable);
    Slice<Review> findAllByMemberIdAndStarLessThanOrderByStarDescIdDesc(Long memberId, Integer star, Pageable pageable);
}
