package umc.global.dto;

import lombok.Builder;

import java.util.List;

public class CusorResDTO {

    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}
}
