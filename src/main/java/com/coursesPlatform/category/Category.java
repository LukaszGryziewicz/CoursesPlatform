package com.coursesPlatform.category;





//@Entity
public class Category {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;

    public Category(String title) {
        this.title = title;
    }

    public Category() {
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
}
