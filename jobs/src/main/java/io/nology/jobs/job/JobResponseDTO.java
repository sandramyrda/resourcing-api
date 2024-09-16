package io.nology.jobs.job;

import java.time.LocalDate;

public class JobResponseDTO {

    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long tempId;

    public JobResponseDTO(Job job) {
        this.id = job.getId();
        this.name = job.getName();
        this.startDate = job.getStartDate();
        this.endDate = job.getEndDate();
        this.tempId = job.getTemp() != null ? job.getTemp().getId() : null;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Long getTempId() {
        return tempId;
    }

}
