package umc.domain.store.dto;

public class StoreReqDTO {
    public record GetStoreInfo(
            Long store_id

    ){}

    // 멤버 번호, 가게 번호로 찾기
    public record GetReviewInfo(
            Long member_id,
            Long store_id
    ){}
}
