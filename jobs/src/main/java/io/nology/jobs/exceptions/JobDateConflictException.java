package io.nology.jobs.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class JobDateConflictException extends RuntimeException {

    public JobDateConflictException(Long tempId, Long jobId) {
        super("Temp with ID " + tempId + " already has a job conflicting with the new job (ID: " + jobId + ").");
    }
}
