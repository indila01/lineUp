package com.example.demo.api;

import com.example.demo.dto.BatchDto;
import com.example.demo.model.Batch;
import com.example.demo.service.BatchService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class BatchApiController {


    @Autowired
    private BatchService batchService;

    @PreAuthorize("hasAnyAuthority('ADMIN','LECTURER','STUDENT')")
    @GetMapping("/batches/all")
    public ResponseEntity<List<BatchDto>> getBatches() {
        return new ResponseEntity<>(convertBatchDto(batchService.getBatches()),HttpStatus.OK );
    }

    public List<BatchDto> convertBatchDto(List<Batch> list) {
        List<BatchDto> Dto = new ArrayList<>();
        for (Batch batch : list) {
            BatchDto batchDto = new BatchDto();
            batchDto.setId(batch.getId());
            batchDto.setBatchCode(batch.getBatchCode());
            batchDto.setCourse(batch.getCourse());
            batchDto.setYear(batch.getYear());

            Dto.add(batchDto);
        }
        return Dto;
    }
}
