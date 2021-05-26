package com.project.bootcamp.controller;

import com.project.bootcamp.model.dto.StockDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> save(@RequestBody StockDTO stock) {
        return ResponseEntity.ok(stock);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> update(@RequestBody StockDTO stock) {
        return ResponseEntity.ok(stock);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockDTO>> findAll() {
        StockDTO stockOne = new StockDTO();
        stockOne.setId(1L);
        stockOne.setName("Magazine Luiza");
        stockOne.setPrice(1000D);
        stockOne.setDate(LocalDate.now());
        return ResponseEntity.ok(Arrays.asList(stockOne));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> findById(@PathVariable Long id) {
        StockDTO stockOne = new StockDTO();
        stockOne.setId(1L);
        stockOne.setName("Magazine Luiza");
        stockOne.setPrice(1000D);
        stockOne.setDate(LocalDate.now());
        StockDTO stockTwo = new StockDTO();
        stockTwo.setId(2L);
        stockTwo.setName("Ponto Frio");
        stockTwo.setPrice(200D);
        stockTwo.setDate(LocalDate.now());
        List<StockDTO> dtos = Arrays.asList(stockOne, stockTwo);
        return ResponseEntity.ok(dtos.stream().filter(s -> s.getId().compareTo(id) == 0).findFirst().get());
    }
}
