package com.coursesPlatform.courses;

import com.coursesPlatform.category.Category;






//@Entity
public class Course {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private Category category;


    public Course(String title, String description, Category category) {
        this.title = title;
        this.description = description;
        this.category = category;
    }

    public Course() {
    }

    public Long getId() {
        return id;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
