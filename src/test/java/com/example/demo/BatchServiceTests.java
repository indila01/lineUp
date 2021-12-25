package com.example.demo;

import com.example.demo.model.Batch;
import com.example.demo.model.Classroom;
import com.example.demo.service.BatchService;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class BatchServiceTests {

    @Autowired
    BatchService batchService;

    @Test
    public void getBatchByBatchCode()  {
        Batch batch = batchService.getBatchByBatchCode("HF2131CNS");

        boolean isExist = false;
        if(!Objects.isNull(batch))
        {
            isExist =true;
        }
        assertTrue(isExist);
    }

    @Test
    public void getBatchById()  {
        Batch batch = batchService.getBatchById(2L);

        boolean isExist = false;
        if(!Objects.isNull(batch))
        {
            isExist =true;
        }
        assertTrue(isExist);
    }
    @Test
    public void getAllBatches()  {
        List<Batch> batches = batchService.getBatches();

        boolean isExist = batches.size()>0;

        assertTrue(isExist);
    }

}
