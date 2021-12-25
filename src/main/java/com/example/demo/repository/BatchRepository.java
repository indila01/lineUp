package com.example.demo.repository;

import com.example.demo.model.Batch;
import com.example.demo.model.Classroom;
import com.example.demo.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatchRepository extends JpaRepository<Batch, Long> {

    Batch findBatchByBatchCode(String batchCode);
    Batch findBatchById(long id);
    List<Batch> findBatchesBySubjects(Subject subject);

}
