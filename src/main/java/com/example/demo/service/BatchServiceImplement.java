package com.example.demo.service;

import com.example.demo.exceptions.CustomException;
import com.example.demo.model.Batch;
import com.example.demo.model.Subject;
import com.example.demo.model.User;
import com.example.demo.repository.BatchRepository;
import com.example.demo.dto.BatchDto;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BatchServiceImplement implements BatchService {
    private BatchRepository batchRepository;
    private SubjectRepository subjectRepository;
    private UserRepository userRepository;

    public BatchServiceImplement(BatchRepository batchRepository, SubjectRepository subjectRepository, UserRepository userRepository) {
        this.batchRepository = batchRepository;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    @Autowired


    @Override
    public List<Batch> getBatches() {
        return batchRepository.findAll();
    }

    @Override
    public Batch getBatchByBatchCode(String batchCode) {
        return batchRepository.findBatchByBatchCode(batchCode);
    }

    @Override
    public Batch getBatchById(long id) {
        return batchRepository.findBatchById(id);
    }

    @Override
    public void deleteBatch(Long id) throws CustomException{

        Batch batch = batchRepository.findById(id).orElseThrow(() -> new CustomException("Batch not found"));
        List<User> usersInBatch = userRepository.findAllByBatch(batch);
        if(!usersInBatch.isEmpty()){
          throw new CustomException("Students are already enrolled to this batch");
        }
        batchRepository.deleteById(id);
        }

    @Override
    public void updateBatch(BatchDto batchDto) throws CustomException {
        Batch batchUpdate = batchRepository.findBatchByBatchCode(batchDto.getBatchCode());

        if (Objects.isNull(batchUpdate)) {
            throw new CustomException("batch does not exist");
        }


        batchUpdate.setBatchCode(batchDto.getBatchCode());
        batchUpdate.setCourse(batchDto.getCourse());
        batchUpdate.setYear(batchDto.getYear());

        List<Subject> subjects = new ArrayList<Subject>();
        List<String> subjectDto = batchDto.getSubjects();

        for(String subject : subjectDto){
            Subject subjectObj = subjectRepository.findSubjectsByName(subject);
            subjects.add(subjectObj);
        }
        batchUpdate.setSubjects(subjects);


        batchRepository.save(batchUpdate);
    }


    @Override
    public Batch createBatch(BatchDto batchDto) throws CustomException {

        Batch exist = batchRepository.findBatchByBatchCode(batchDto.getBatchCode());
        if(!Objects.isNull(exist)){
            throw new CustomException("Batch already exist");
        }
        Batch batch = new Batch();
        batch.setBatchCode(batchDto.getBatchCode());
        batch.setCourse(batchDto.getCourse());
        batch.setYear(batchDto.getYear());

        List<Subject> subjects = new ArrayList<Subject>();
        List<String> subjectDto = batchDto.getSubjects();

        for(String subject : subjectDto){
            Subject subjectObj = subjectRepository.findSubjectsByName(subject);
            subjects.add(subjectObj);
        }
        batch.setSubjects(subjects);


        return batchRepository.save(batch);
    }


}
