package com.example.stock;

import com.example.stock.dto.StockDto;
import com.example.stock.entity.Stock;
import com.example.stock.repository.StockRepository;
import com.example.stock.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class StockServiceUnitTest {
/*

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockService stockService;

    private Stock stock;
    private StockDto dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        stock = new Stock();
        stock.setStockId("555");
        stock.setProductId("999");
        stock.setStock(6L);

        dto = new StockDto("555", "999", 10L);
    }

    @Test
    void createStock_단위테스트() {
        // When
        when(stockRepository.save(any(Stock.class))).thenReturn(stock);
        Stock createdStock = stockService.createStock(dto);

        // Then
        assertNotNull(createdStock);
        assertEquals("555", createdStock.getStockId());
        assertEquals("999", createdStock.getProductId());
        assertEquals(6L, createdStock.getStock());

        verify(stockRepository, times(1)).save(any(Stock.class));
    }

    @Test
    void getStock_단위테스트() {
        // Given
        when(stockRepository.findByStockId("555")).thenReturn(Optional.of(stock));

        // When
        Stock fetchedStock = stockService.getStock("555");

        // Then
        assertNotNull(fetchedStock);
        assertEquals("555", fetchedStock.getStockId());
        assertEquals("999", fetchedStock.getProductId());
        assertEquals(6L, fetchedStock.getStock());

        verify(stockRepository, times(1)).findByStockId("555");
    }

    @Test
    void getStock_예외처리_단위테스트() {
        // Given
        when(stockRepository.findByStockId("555")).thenReturn(Optional.empty());

        // When
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            stockService.getStock("555");
        });

        // Then
        assertEquals("재고가 존재하지 않습니다", exception.getMessage());

        verify(stockRepository, times(1)).findByStockId("555");
    }

    @Test
    void updateStock_단위테스트() {
        // Given
        when(stockRepository.findByStockId("555")).thenReturn(Optional.of(stock));
        when(stockRepository.save(any(Stock.class))).thenReturn(stock);

        // When
        Stock updatedStock = stockService.updateStock("555", dto);

        // Then
        assertNotNull(updatedStock);
        assertEquals(10L, updatedStock.getStock());

        verify(stockRepository, times(1)).findByStockId("555");
        verify(stockRepository, times(1)).save(any(Stock.class));
    }

    @Test
    void decreaseStock_단위테스트() {
        // Given
        when(stockRepository.findByStockId("555")).thenReturn(Optional.of(stock));
        when(stockRepository.save(any(Stock.class))).thenReturn(stock);

        // When
        Stock decreasedStock = stockService.decreaseStock("555", 3L);

        // Then
        assertNotNull(decreasedStock);
        assertEquals(3L, decreasedStock.getStock());
        verify(stockRepository, times(1)).findByStockId("555");
        verify(stockRepository, times(1)).save(any(Stock.class));
    }

    @Test
    void decreaseStock_예외처리_단위테스트() {
        // Given
        when(stockRepository.findByStockId("555")).thenReturn(Optional.of(stock));

        // When
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            stockService.decreaseStock("555", 10L);
        });

        // Then
        assertEquals("재고가 충분하지 않습니다", exception.getMessage());
        verify(stockRepository, times(1)).findByStockId("555");
    }

    @Test
    void deleteStock_단위테스트() {
        // Given
        when(stockRepository.findByStockId("555")).thenReturn(Optional.of(stock));

        // When
        stockService.deleteStock("555");

        // Then
        verify(stockRepository, times(1)).delete(stock);
    }

    @Test
    void deleteStock_재고없음_단위테스트() {
        // Given
        when(stockRepository.findByStockId("555")).thenReturn(Optional.empty());

        // When
        assertThatThrownBy(() -> stockService.deleteStock("555"))
                .isInstanceOf(IllegalArgumentException.class)  // 예외 타입 검증
                .hasMessage("재고가 존재하지 않습니다");

        // Then
        verify(stockRepository, times(1)).findByStockId("555");
    }
*/

}
