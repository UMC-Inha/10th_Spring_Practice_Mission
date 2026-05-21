package umc.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.review.entity.ReviewReply;

import java.util.List;

public interface ReviewReplyRepository extends JpaRepository<ReviewReply, Long> {

    // review 필드 안의 id 탐색
    List<ReviewReply> findByReview_Id(Long reviewId);
}
