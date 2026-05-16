package umc.global.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PageResDTO {

    //페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){}
}
