package com.coursesPlatform.courses;


import com.coursesPlatform.category.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Component
public class InMemoryCourseRepository {

    private HashMap<Long, Course> courseHashMap = new HashMap<>();

    public List<Course> getCoursesList() {
        return new ArrayList<Course>(courseHashMap.values());
    }
    public void add(Course course){
        courseHashMap.put(course.getId(),course);
    }
}
