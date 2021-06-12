package com.coursesPlatform;

import com.coursesPlatform.category.Category;
import com.coursesPlatform.courses.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class InMemoryRepository {

    private HashMap<Course, Category> coursesList = new HashMap<>();

    public List<Course> getCoursesList() {
        return new ArrayList<Course>(coursesList.keySet());

    }
    public List<Category> getCategoryList() {
        return new ArrayList<Category>(coursesList.values());

    }

    public void setCoursesList(HashMap<Course, Category> coursesList) {
        this.coursesList = coursesList;
    }

}
