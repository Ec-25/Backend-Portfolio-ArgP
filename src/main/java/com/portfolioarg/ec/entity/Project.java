package com.portfolioarg.ec.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String icon;
    @NotNull
    @Size(min = 2, max = 63, message = "does not meet the length(2:63)")
    private String project;
    @NotNull
    @Size(min = 2, max = 255, message = "does not meet the length(2:255)")
    private String text;
    @NotNull
    private String link_gh;
    private String link_page;

    // Cnts
    public Project() {
    }
    public Project(String icon,
            @NotNull @Size(min = 2, max = 63, message = "does not meet the length(2:63)") String project,
            @NotNull @Size(min = 2, max = 255, message = "does not meet the length(2:255)") String text,
            @NotNull String link_gh, String link_page) {
        this.icon = icon;
        this.project = project;
        this.text = text;
        this.link_gh = link_gh;
        this.link_page = link_page;
    }

    // Gts&Sts
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getProject() {
        return project;
    }
    public void setProject(String project) {
        this.project = project;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getLink_gh() {
        return link_gh;
    }
    public void setLink_gh(String link_gh) {
        this.link_gh = link_gh;
    }
    public String getLink_page() {
        return link_page;
    }
    public void setLink_page(String link_page) {
        this.link_page = link_page;
    }

}
