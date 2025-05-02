package com.example.store;

import com.example.store.dto.StoreDto;
import com.example.store.entity.Store;
import com.example.store.repository.StoreRepository;
import com.example.store.service.StoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@Transactional
@Rollback(false)
class StoreServiceIntegrationTest {
    // 통합테스트(Integration Test) : 실제 데이터베이스나 외부 API를 사용 실제 환경에서의 동작을 검증

    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreRepository storeRepository;

    @Test
    void createStore_통합테스트() {
        // given
        StoreDto dto = new StoreDto("test", "naeun", "sejong", "010-0000-0000");

        // when
        Store saved = storeService.createStore(dto);

        // then
        String storeId = saved.getStoreId();
        Store found = storeRepository.findByStoreId(storeId).orElseThrow();

        assertThat(found.getStoreName()).isEqualTo("test");
    }

    @Test
    void getStore_통합테스트() {
        // given
        StoreDto dto = new StoreDto("test", "naeun", "sejong", "010-0000-0000");
        Store saved = storeService.createStore(dto);

        // when
        Store found = storeService.getStore(saved.getStoreId());

        // then
        assertThat(found).isNotNull();
        assertThat(found.getStoreName()).isEqualTo("test");
        assertThat(found.getAddress()).isEqualTo("sejong");
    }

    @Test
    void updateStore_통합테스트() {
        // given
        StoreDto createDto = new StoreDto("test 1", "naeun", "1", "010-1111-1111");
        Store saved = storeService.createStore(createDto);

        // when
        StoreDto updateDto = new StoreDto("t 2", "namwoo", "2", "010-2222-2222");
        Store updated = storeService.updateStore(saved.getStoreId(), updateDto);

        // then
        assertThat(updated).isNotNull();
        assertThat(updated.getStoreName()).isEqualTo("t 2");
        assertThat(updated.getOwnerName()).isEqualTo("namwoo");
        assertThat(updated.getAddress()).isEqualTo("2");
        assertThat(updated.getPhoneNumber()).isEqualTo("010-2222-2222");
    }

    @Test
    void deleteStore_통합테스트() {
        // given
        StoreDto dto = new StoreDto("test 3", "heeyeon", "daegu", "010-1111-1111");
        Store saved = storeService.createStore(dto);

        // when
        storeService.deleteStore(saved.getStoreId());

        // then
        assertThatThrownBy(() -> storeService.getStore(saved.getStoreId()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 가게가 존재하지 않습니다");
    }

}
