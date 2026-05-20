package umc.domain.review.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.review.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Slice<Review> findByMember_IdAndIdLessThanOrderByIdDesc(Long memberId, long idCursor, PageRequest pageRequest);

    @Query("""
        select r
        from Review r
        where r.member.id = :memberId
            and (
                r.rating < :ratingCursor
                or (r.rating = :ratingCursor and r.id < :idCursor)
            )
        order by r.rating desc, r.id desc
    """)
    Slice<Review> findNextByMemberIdAndRatingCursor(
            @Param("memberId") Long memberId,
            @Param("ratingCursor") Integer ratingCursor,
            @Param("idCursor") Long idCursor,
            Pageable pageable
    );

}
