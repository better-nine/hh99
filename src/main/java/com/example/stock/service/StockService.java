package com.example.stock.service;

import com.example.stock.dto.StockDto;
import com.example.stock.entity.Stock;
import com.example.stock.repository.StockRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    /**
     * 재고 등록
     * @param dto
     * @return
     */
    public Stock createStock(StockDto dto) {
        Stock stock = Stock.builder()
                .stockId(UUID.randomUUID().toString())
                .storeId(dto.getStoreId())
                .productId(dto.getProductId())
                .stock(dto.getStock())
                .build();
        return stockRepository.save(stock);
    }

    /**
     * 재고 조회
     * @param stockId
     * @return
     */
    public Stock getStock(String stockId) {
        return stockRepository.findByStockId(stockId)
                .orElseThrow(() -> new IllegalArgumentException("Stock not found"));
    }

    /**
     * 재고 정보 수정
     * @param stockId
     * @param dto
     * @return
     * @throws JsonMappingException
     */
    public Stock updateStock(String stockId, StockDto dto) throws JsonMappingException {
        Stock stock = stockRepository.findByStockId(stockId)
                .orElseThrow(() -> new IllegalArgumentException("Stock not found"));

        stock.setStoreId(dto.getStoreId());
        stock.setProductId(dto.getProductId());
        stock.setStock(dto.getStock());

        return stockRepository.save(stock);
    }

    /**
     * 재고 수량 감소
     * @param stockId
     * @param quantity
     * @return
     */
    @Transactional
    public Stock decreaseStock(String stockId, Long quantity) {
        Stock stock = stockRepository.findByStockId(stockId)
                .orElseThrow(() -> new IllegalArgumentException("Stock not found"));

        boolean result = stock.decrease(quantity);
        if (!result) {
            throw new IllegalStateException("Not enough stock");
        }

        return stockRepository.save(stock);
    }

    /**
     * 재고 삭제
     * @param stockId
     */
    public void deleteStock(String stockId) {
        Stock stock = stockRepository.findByStockId(stockId)
                .orElseThrow(() -> new IllegalArgumentException("Stock not found"));

        stockRepository.delete(stock);
    }
}
