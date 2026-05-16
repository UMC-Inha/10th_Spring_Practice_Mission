package umc.domain.review.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.review.entity.Review;

import java.math.BigDecimal;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
            SELECT r FROM Review r
            JOIN FETCH r.member
            WHERE r.member.id = :memberId
                AND r.id < :idCursor
            ORDER BY r.id DESC
            """)
    Slice<Review> findByMemberIdOrderById(
            @Param("memberId") Long memberId,
            @Param("idCursor") Long idCursor,
            PageRequest pageRequest
    );

    @Query("""
            SELECT r FROM Review r
            JOIN FETCH r.member
            WHERE r.member.id = :memberId
                AND (r.star < :starCursor
                    OR (r.star = :starCursor AND r.id > :idCursor))
            ORDER BY r.star DESC, r.id ASC
            """)
    Slice<Review> findByMemberIdOrderByStar(
            @Param("memberId") Long memberId,
            @Param("idCursor") Long idCursor,
            @Param("starCursor") BigDecimal starCursor,
            PageRequest pageRequest
    );
}
