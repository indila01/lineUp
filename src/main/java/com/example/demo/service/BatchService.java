package com.example.demo.service;

import com.example.demo.exceptions.CustomException;
import com.example.demo.model.Batch;
import com.example.demo.dto.BatchDto;

import java.util.List;

public interface BatchService {
    Batch createBatch(BatchDto batchDto) throws CustomException;
    List<Batch> getBatches();
    Batch getBatchByBatchCode(String batchCode);
    Batch getBatchById(long id);
    void deleteBatch(Long id) throws CustomException;
    void updateBatch(BatchDto batchDto) throws CustomException;
}
