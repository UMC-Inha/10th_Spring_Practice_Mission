package umc.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.store.converter.StoreConverter;
import umc.domain.store.dto.StoreReqDTO;
import umc.domain.store.dto.StoreResDTO;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreResDTO.GetInfo getInfo(StoreReqDTO.GetInfo dto) {
        Long storeId = dto.store_id();
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));
        return StoreConverter.toGetInfo(store);
    }
}