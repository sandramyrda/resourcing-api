package io.nology.jobs.job;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public class CreateJobDTO {

    @NotBlank
    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long tempId;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStartDate(LocalDate starDate) {
        this.startDate = starDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }

    public Long getTempId() {
        return tempId;
    }
}
