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
    @OneToMany(mappedBy = "course", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Lecture> lectures = new ArrayList<>();

    Course(String title, String description) {
        this.title = title;
        this.description = description;
    }

    Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    void addToLectures(Lecture lecture) {
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
