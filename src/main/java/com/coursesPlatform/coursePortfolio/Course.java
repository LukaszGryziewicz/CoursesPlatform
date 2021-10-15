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
    @Column(length = 1000)
    private String description;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Lecture> lectures = new ArrayList<>();

    Course(String title, String description) {
        this.title = title;
        this.description = description;
    }

    Course() {
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

    void add(Lecture lecture) {
        lectures.add(lecture);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", lectures=" + lectures +
                '}';
    }
}
