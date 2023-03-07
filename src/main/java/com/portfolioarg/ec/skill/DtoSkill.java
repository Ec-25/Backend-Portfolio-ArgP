package com.portfolioarg.ec.skill;

import javax.validation.constraints.NotBlank;

public class DtoSkill {
    @NotBlank
    private String name;
    @NotBlank
    private int percentage;

    // Cnts
    public DtoSkill() {
    }
    
    public DtoSkill(@NotBlank String name, @NotBlank int percentage) {
        this.name = name;
        this.percentage = percentage;
    }

    // Gts&Sts
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

}
