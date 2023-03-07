package com.portfolioarg.ec.person;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DtoPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(min = 2, max = 63, message = "does not meet the length(2:63)")
    private String name;
    @NotNull
    @Size(min = 2, max = 31, message = "does not meet the length(2:31)")
    private String title;
    @NotNull
    @Size(min = 2, max = 255, message = "does not meet the length(2:255)")
    private String labels;
    private String photo;
    @NotNull
    @Size(min = 32, message = "does not meet the length(about > 32)")
    private String about;

    // Cnts
    public DtoPerson() {
    }

    public DtoPerson(@NotNull @Size(min = 2, max = 63, message = "does not meet the length(2:63)") String name,
            @NotNull @Size(min = 2, max = 31, message = "does not meet the length(2:31)") String title,
            @NotNull @Size(min = 2, max = 255, message = "does not meet the length(2:255)") String labels,
            String photo,
            @NotNull @Size(min = 32, message = "does not meet the length(about > 32)") String about) {
        this.name = name;
        this.title = title;
        this.labels = labels;
        this.photo = photo;
        this.about = about;
    }

    // Gts&Sts
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

}
