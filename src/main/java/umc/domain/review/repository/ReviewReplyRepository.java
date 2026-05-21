package umc.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.review.entity.ReviewReply;

import java.util.List;

public interface ReviewReplyRepository extends JpaRepository<ReviewReply, Long> {

    // 리뷰 ID로 답글 조회 -> N+1 문제 해결
    List<ReviewReply> findByReview_IdIn(List<Long> reviewIds);
}
