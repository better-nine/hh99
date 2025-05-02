package com.example.store.service;

import com.example.store.dto.StoreDto;
import com.example.store.entity.Store;
import com.example.store.repository.StoreRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final ObjectMapper mapper;

    /**
     * 가게 등록
     * @param dto
     * @return
     */
    public Store createStore(StoreDto dto) {
        Store store = Store.builder()
                .storeId(UUID.randomUUID().toString())
                .storeName(dto.getStoreName())
                .ownerName(dto.getOwnerName())
                .address(dto.getAddress())
                .phoneNumber(dto.getPhoneNumber())
                .build();
        return storeRepository.save(store);
    }

    /**
     * 가게 조회
     * @param storeId
     * @return
     */
    public Store getStore(String storeId) {
        return storeRepository.findByStoreId(storeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 가게가 존재하지 않습니다."));
    }


    /**
     * 가게 수정
     * @param storeId
     * @param dto
     * @return
     * @throws JsonMappingException
     */
    public Store updateStore(String storeId, StoreDto dto) {
        Store store = storeRepository.findByStoreId(storeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 가게가 존재하지 않습니다."));

        store.setStoreName(dto.getStoreName());
        store.setOwnerName(dto.getOwnerName());
        store.setAddress(dto.getAddress());
        store.setPhoneNumber(dto.getPhoneNumber());

        return storeRepository.save(store);
    }

    /**
     * 가게 삭제
     * @param storeId
     */
    public void deleteStore(String storeId) {
        Store store = storeRepository.findByStoreId(storeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 가게가 존재하지 않습니다."));
        storeRepository.delete(store);
    }


}
