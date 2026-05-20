package umc.apiPayload.dto;

import lombok.Builder;

import java.util.List;

public class BaseResDTO {
    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {}

    @Builder
    public record SliceResponse<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {}
}
