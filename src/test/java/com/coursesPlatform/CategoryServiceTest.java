package com.coursesPlatform;

import com.coursesPlatform.category.Category;
import com.coursesPlatform.category.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@Transactional
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private InMemoryRepository inMemoryRepository;
    @Test
    void shouldAddCategory() {
        //given
        Category category = new Category("title");
        //when
        categoryService.addNewCategory(category);
        //then
        assertThat(inMemoryRepository.getCategoryList()).containsExactlyInAnyOrder(category);
    }
}
