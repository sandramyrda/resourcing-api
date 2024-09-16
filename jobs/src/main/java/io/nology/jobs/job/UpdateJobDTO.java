package io.nology.jobs.job;

import java.time.LocalDate;

public class UpdateJobDTO {

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long tempId;

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Long getTempId() {
        return tempId;
    }

}
