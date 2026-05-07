package umc.domain.store.dto;

import lombok.Builder;

public class StoreResDTO {
    @Builder
    public record GetInfo(
            String store_nm,
            String region_nm,
            String open_dt,
            String close_dt,
            String chg_dt,
            String chg_tm
    ){}

}
