package com.example.demo.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name =  "Subject", uniqueConstraints = @UniqueConstraint(columnNames = "subject_name"))
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject_name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Classroom> classes;

    @ManyToMany( mappedBy = "subjects")
    private List <Batch> batches;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE
            , CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Classroom> getClasses() {
        return classes;
    }

    public void setClasses(Collection<Classroom> classes) {
        this.classes = classes;
    }

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }
}


