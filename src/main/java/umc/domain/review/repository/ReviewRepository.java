package umc.domain.review.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umc.domain.review.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query("""
            select r
            from Review r
                join fetch r.member m
            where r.member.id = :memberId
                and r.id > :idCursor
            order by r.id asc
    """)
    Slice<Review> findReviewsByMemberIdInOrderByReviewId(
            @Param("memberId") Long memberId,
            @Param("idCursor") Long idCursor,
            PageRequest pageRequest
    );

    @Query("""
            select r
            from Review r
                join fetch r.member m
            where r.member.id = :memberId
                and (
                    r.starRating < :ratingCursor 
                        or (r.starRating = :ratingCursor and r.id > :idCursor)
                    )
            order by r.starRating desc, r.id asc
    """)
    Slice<Review> findReviewsByMemberIdOrderByRating(
            @Param("memberId") Long memberId,
            @Param("idCursor") Long idCursor,
            @Param("ratingCursor") Integer ratingCursor,
            PageRequest pageRequest
    );
}
