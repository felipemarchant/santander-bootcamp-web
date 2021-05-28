package com.project.bootcamp.service;

import com.project.bootcamp.exception.BusinessException;
import com.project.bootcamp.exception.NotFoundException;
import com.project.bootcamp.mapper.StockMapper;
import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.repository.StockRepository;
import com.project.bootcamp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private final StockRepository repository;
    private final StockMapper mapper;

    @Autowired
    public StockService(StockRepository repository, StockMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public StockDTO save(StockDTO dto) {
        Optional<Stock> existsStock = repository.findByNameAndDate(dto.getName(), dto.getDate());
        existsStock.ifPresent(s -> { throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS); });
        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDTO(stock);
    }

    @Transactional
    public StockDTO update(StockDTO dto) {
        Optional<Stock> existsStock = repository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId());
        existsStock.ifPresent(s -> { throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS); });
        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDTO(stock);
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    @Transactional(readOnly = true)
    public StockDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDTO).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(findById(id).getId());
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findToday() {
        return repository.findToday(LocalDate.now()).map(mapper::toDTO).orElseThrow(NotFoundException::new);
    }
}
