package com.coursesPlatform.category;

import com.coursesPlatform.courses.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class InMemoryCategoryRepository {
    private HashMap<Long, Category> categoryHashMap = new HashMap<>();

    public List<Category> getCategoryList() {
        return new ArrayList<Category>(categoryHashMap.values());
    }

    public void add(Category category){
        categoryHashMap.put(category.getId(),category);
    }
}
