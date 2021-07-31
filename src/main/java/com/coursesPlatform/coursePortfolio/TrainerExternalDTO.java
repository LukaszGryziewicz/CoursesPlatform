package com.coursesPlatform.coursePortfolio;

import java.util.List;
import java.util.Objects;

public class TrainerExternalDTO {
    private final String name;
    private final  String lastName;
    private String biography;

    public TrainerExternalDTO(String name, String lastName, String biography) {
        this.name = name;
        this.lastName = lastName;
        this.biography = biography;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainerExternalDTO that = (TrainerExternalDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(biography, that.biography);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, biography);
    }
}
