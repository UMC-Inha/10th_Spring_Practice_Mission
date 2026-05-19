package umc.domain.review.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.member.entity.Member;
import umc.domain.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
    select r from Review r
    where r.member.id = :memberId
    and r.id > :idCursor
    order by r.id asc
    """)
    Slice<Review> findReviewsByMemberIdInOrderByReviewId(@Param("memberId") Long memberId,@Param("idCursor") Long idCursor, PageRequest pageRequest);

    @Query("""
    select r from Review r
    where r.member.id = :memberId
    and (
        r.rate < :ratingCursor
            or (r.rate = :ratingCursor and r.id > :idCursor)
        )
    order by r.rate desc, r.id desc
    """)
    Slice<Review> findAllByMemberIdOrderByRatingDesc(@Param("memberId") Long memberId,@Param("ratingCursor") Double ratingCursor,  @Param("idCursor") Long idCursor, PageRequest pageRequest);

    Slice<Review> findAllByMember(Member memberId, PageRequest pageRequest);
}
