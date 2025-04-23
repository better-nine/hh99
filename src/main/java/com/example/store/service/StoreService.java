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


        return null;
    }

    /**
     * 가게 조회
     * @param storeId
     * @return
     */
    public Store getStore(String storeId) {


        return null;
    }

    /**
     * 가게 수정
     * @param storeId
     * @param dto
     * @return
     * @throws JsonMappingException
     */
    public Store updateStore(String storeId, StoreDto dto) throws JsonMappingException {

        return null;
    }

    /**
     * 가게 삭제
     * @param storeId
     */
    public void deleteStore(String storeId) {

    }


}
