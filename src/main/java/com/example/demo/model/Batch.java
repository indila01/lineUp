package com.example.demo.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name =  "batch", uniqueConstraints = @UniqueConstraint(columnNames = "batch_code"))
public class Batch {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "batch_code")
    private String batchCode;
    private String year;
    private String course;

    @OneToMany( fetch = FetchType.LAZY,mappedBy = "batch", orphanRemoval = true)
    private Collection<User> users;

    @ManyToMany()
    @JoinTable(
            name = "batch_subjects",
            joinColumns = @JoinColumn(name = "batch_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;

    public Batch() {
    }

    public Batch(String batchCode, String year, String course) {
        this.batchCode = batchCode;
        this.year = year;
        this.course = course;
    }

    public Batch(String batchCode, String year, String course, Collection<User> users) {
        this.batchCode = batchCode;
        this.year = year;
        this.course = course;
        this.users = users;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
