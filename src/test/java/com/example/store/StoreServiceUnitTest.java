package com.example.store;

import com.example.store.dto.StoreDto;
import com.example.store.entity.Store;
import com.example.store.repository.StoreRepository;
import com.example.store.service.StoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

class StoreServiceUnitTest {
    // 단위테스트 : 보통 목 객체를 사용하여 외부 의존성을 제거하고, 테스트 대상 코드만을 검증

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private StoreService storeService;

    String storeId = "999999999";
    Store store;
    StoreDto dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        store = new Store();
        store.setStoreName("test");
        store.setOwnerName("naeun");
        store.setAddress("sejong");
        store.setPhoneNumber("010-0000-0000");

        dto = new StoreDto("store", "naeun", "sejong", "010-0000-0000");

    }

    @Test
    void createStore_단위테스트() {
        // When
        when(storeRepository.save(any(Store.class))).thenReturn(store); // 이걸로 mock을 만들어서 실제 데이터베이스에 저장되지 않음
        Store createdStore = storeService.createStore(dto);

        // Then
        assertNotNull(createdStore);
        assertEquals("test", createdStore.getStoreName());
        assertEquals("naeun", createdStore.getOwnerName());
        assertEquals("sejong", createdStore.getAddress());
        assertEquals("010-0000-0000", createdStore.getPhoneNumber());

        verify(storeRepository, times(1)).save(any(Store.class));
    }

    @Test
    void getStore_단위테스트() {
        // When
        when(storeRepository.findByStoreId(storeId)).thenReturn(java.util.Optional.of(store));
        Store foundStore = storeService.getStore(storeId);

        // Then
        assertNotNull(foundStore);
        assertEquals("test", foundStore.getStoreName());
        assertEquals("sejong", foundStore.getAddress());

        verify(storeRepository, times(1)).findByStoreId(storeId);
    }

    @Test
    void updateStore_단위테스트() {
        // Given
        StoreDto updateDto = new StoreDto("test store 2", "namwoo", "2", "010-2222-2222");

        Store updatedStore = new Store();
        updatedStore.setStoreName(updateDto.getStoreName());
        updatedStore.setOwnerName(updateDto.getOwnerName());
        updatedStore.setAddress(updateDto.getAddress());
        updatedStore.setPhoneNumber(updateDto.getPhoneNumber());

        // When
        when(storeRepository.findByStoreId(storeId)).thenReturn(java.util.Optional.of(store));
        when(storeRepository.save(any(Store.class))).thenReturn(updatedStore);
        Store result = storeService.updateStore(storeId, updateDto);

        // Then
        assertNotNull(result);
        assertThat(result.getStoreName()).isEqualTo(updateDto.getStoreName());
        assertThat(result.getOwnerName()).isEqualTo(updateDto.getOwnerName());
        assertThat(result.getAddress()).isEqualTo(updateDto.getAddress());
        assertThat(result.getPhoneNumber()).isEqualTo(updateDto.getPhoneNumber());

        verify(storeRepository, times(1)).findByStoreId(storeId);
        verify(storeRepository, times(1)).save(any(Store.class));
    }

    @Test
    void deleteStore_단위테스트() {
        // When
        when(storeRepository.findByStoreId(storeId)).thenReturn(java.util.Optional.of(store));

        // Then
        storeService.deleteStore(storeId);

        verify(storeRepository, times(1)).findByStoreId
                (storeId);
        verify(storeRepository, times(1)).delete(any(Store.class));
    }
}
