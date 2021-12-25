package com.example.demo.service;

import com.example.demo.exceptions.CustomException;
import com.example.demo.model.Batch;
import com.example.demo.model.Classroom;
import com.example.demo.model.Subject;
import com.example.demo.model.User;
import com.example.demo.repository.ClassroomRepository;
import com.example.demo.dto.ClassroomDto;
import com.example.demo.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassroomServiceImplement implements ClassroomService {


    private ClassroomRepository classroomRepository;
    private SubjectRepository subjectRepository;


    @Autowired
    public ClassroomServiceImplement(ClassroomRepository classroomRepository, SubjectRepository subjectRepository) {
        this.classroomRepository = classroomRepository;
        this.subjectRepository = subjectRepository;
    }

    //    @PostConstruct
//    public void test () {
//       ClassroomDto classroomDto = new ClassroomDto();
//       classroomDto.setClassName("asss");
//       classroomDto.setDate("ASAD");
//       classroomDto.setEndTime("asd");
//       classroomDto.setStartTime("asd");
//       classroomDto.setSubject(subjectService.getSubject(1));
//       createClassroom(classroomDto);
//    }
    @Override
    public Classroom createClassroom(ClassroomDto classroomDto) {


        Classroom classroom = new Classroom();
        classroom.setClassName(classroomDto.getClassName());
        classroom.setDate(classroomDto.getDate());
        classroom.setStartTime(classroomDto.getStartTime());
        classroom.setEndTime(classroomDto.getEndTime());
        classroom.setSubject(subjectRepository.findSubjectsByName(classroomDto.getSubject()));

        return classroomRepository.save(classroom);
    }

    @Override
    public List<Classroom> getClassrooms() {
        return classroomRepository.findAll();
    }

    @Override
    public List<Classroom> getClassroomsBySubject(long id) {
        return classroomRepository.findClassroomBySubjectIdOrderByDateAsc(id);
    }

    public List<Classroom> getClassroomsByBatchAndDate(Batch batch,String date) {
        List<Classroom> classroomList = new ArrayList<>();

        List<Subject> subjectInBatch = subjectRepository.findSubjectsByBatches(batch);

        for (Subject subject : subjectInBatch) {
            List<Classroom> classroomsForEachSubject = classroomRepository.findClassroomsBySubjectAndDateOrderByDateAsc(subject,date);
            classroomList.addAll(classroomsForEachSubject);
        }
        return classroomList;
    }

    //test
    @Override
    public List<Classroom> getClassroomsByBatch(Batch batch) {
        List<Classroom> classroomList = new ArrayList<>();

        List<Subject> subjectInBatch = subjectRepository.findSubjectsByBatches(batch);

        for (Subject subject : subjectInBatch) {
            List<Classroom> classroomsForEachSubject = classroomRepository.findClassroomsBySubjectOrderByDateAsc(subject);
            classroomList.addAll(classroomsForEachSubject);
        }
        return classroomList;
    }

    @Override
    public List<Classroom> getClassroomsByUser(User user) {
        List<Classroom> classroomList = new ArrayList<>();

        List<Subject> subjectsOfUser = subjectRepository.findSubjectsByUser(user);

        for (Subject subject : subjectsOfUser) {
            List<Classroom> classroomsForEachSubject = classroomRepository.findClassroomsBySubjectOrderByDateAsc(subject);
            classroomList.addAll(classroomsForEachSubject);
        }
        return classroomList;
    }

    @Override
    public void deleteClassroom(long id) throws CustomException {
        classroomRepository.findById(id).orElseThrow(() -> new CustomException("class not found"));
        classroomRepository.deleteById(id);
    }


}
