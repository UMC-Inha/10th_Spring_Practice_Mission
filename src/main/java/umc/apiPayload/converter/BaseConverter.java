package umc.apiPayload.converter;

import umc.apiPayload.dto.BaseResDTO;

import java.util.List;

public class BaseConverter {
    public static <T> BaseResDTO.SliceResponse<T> toSliceResponse(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){
        return BaseResDTO.SliceResponse.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}
