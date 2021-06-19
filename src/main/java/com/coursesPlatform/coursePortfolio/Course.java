package com.coursesPlatform.coursePortfolio;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String title;
    @Column(length = 300)
    private String description;
    @ManyToOne
    private Category category;
    @OneToMany(mappedBy = "course", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Lecture> lectures = new ArrayList<>();

    Course(String title, String description) {
        this.title = title;
        this.description = description;

    }

    Course() {
    }

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    List<Lecture> getLectures() {
        return lectures;
    }

    void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }
}
