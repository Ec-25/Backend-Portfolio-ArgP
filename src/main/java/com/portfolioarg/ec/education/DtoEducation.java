package com.portfolioarg.ec.education;

import javax.validation.constraints.NotBlank;

public class DtoEducation {
    @NotBlank
    private String education;
    @NotBlank
    private String institution;
    
    // Cnts
    public DtoEducation() {
    }

    public DtoEducation(@NotBlank String education, @NotBlank String institution) {
        this.education = education;
        this.institution = institution;
    }

    // Gts&Sts
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getInstitution() {
        return institution;
    }
    
    public void setInstitution(String institution) {
        this.institution = institution;
    }

}
