package umc.domain.store.converter;

import umc.domain.store.dto.StoreResDTO;
import umc.domain.store.entity.Store;

public class StoreConverter {
    public static StoreResDTO.GetInfo toGetInfo(
            Store store
    ) {
        return StoreResDTO.GetInfo.builder()
                .store_nm(store.getStore_nm())
                .region_nm(store.getRegion_nm())
                .open_dt(store.getOpen_dt())
                .close_dt(store.getClose_dt())
                .chg_dt(store.getChg_dt())
                .chg_tm(store.getChg_tm())
                .build();
    }
}
