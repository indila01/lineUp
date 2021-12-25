package com.example.demo.service;

import com.example.demo.exceptions.CustomException;
import com.example.demo.model.Batch;
import com.example.demo.model.Subject;
import com.example.demo.model.User;
import com.example.demo.repository.BatchRepository;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.dto.SubjectDto;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SubejectServiceImplement implements SubjectService {

    private SubjectRepository subjectRepository;
    private BatchRepository batchRepository;
    private UserRepository userRepository;

    @Autowired
    public SubejectServiceImplement(SubjectRepository subjectRepository, BatchRepository batchRepository, UserRepository userRepository) {
        this.subjectRepository = subjectRepository;
        this.batchRepository = batchRepository;
        this.userRepository = userRepository;
    }




    @Override
    public Subject createSubject(SubjectDto subjectDto) throws CustomException {
        Subject exist = subjectRepository.findSubjectsByName(subjectDto.getName());

        if (!Objects.isNull(exist)) {
            throw new CustomException("Subject already exists");
        }

        Subject subject = new Subject();
        subject.setName(subjectDto.getName());
        User lecturer = userRepository.findUserByUsername(subjectDto.getUser());
        if (Objects.isNull(lecturer)) {
            throw new CustomException("Lecturer does not exist");
        }
        subject.setUser(lecturer);
        return subjectRepository.save(subject);

    }

    @Override
    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public List<Subject> getSubjectsByBatch(Batch batch) {
        return subjectRepository.findSubjectsByBatches(batch);
    }

    @Override
    public Subject getSubject(long id) {
        return subjectRepository.findSubjectById(id);
    }

    @Override
    public void deleteSubject(long id) throws CustomException {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new CustomException("Subject not found"));
        List<Batch> subjectInBatches = batchRepository.findBatchesBySubjects(subject);
        if (!subjectInBatches.isEmpty()) {
            throw new CustomException("Subject is in one or more batches");
        }
        subjectRepository.deleteById(id);
    }


}
