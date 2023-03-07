package com.portfolioarg.ec.experience;

import javax.validation.constraints.NotBlank;

public class DtoExperience {
    @NotBlank
    private String experience;
    @NotBlank
    private String description;

    // Cnts
    public DtoExperience() {
    }

    public DtoExperience(@NotBlank String experience, @NotBlank String description) {
        this.experience = experience;
        this.description = description;
    }

    // Gts&Sts
    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
