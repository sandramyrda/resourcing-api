package io.nology.jobs.job;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nology.jobs.exceptions.JobDateConflictException;
import io.nology.jobs.exceptions.JobNotFoundException;
import io.nology.jobs.exceptions.TempNotFoundException;
import io.nology.jobs.temp.Temp;
import io.nology.jobs.temp.TempRepository;
import io.nology.jobs.temp.TempService;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepo;

    @Autowired
    private TempRepository tempRepo;

    @Autowired
    private TempService tempService;

    public List<Job> findAll() {
        return this.jobRepo.findAll();
    }

    public Job createJob(CreateJobDTO data) {
        Job job = new Job();
        job.setName(data.getName());
        job.setStartDate(data.getStartDate());
        job.setEndDate(data.getEndDate());

        if (data.getTempId() != null) {
            job.setTemp(tempService.getTempById(data.getTempId()));
        }

        return jobRepo.save(job);
    }

    public Job getJobById(Long jobId) {
        return jobRepo.findById(jobId).orElseThrow(() -> new JobNotFoundException(jobId));
    }

    public Job updateJob(Long jobId, UpdateJobDTO updateJobDTO) {
        Job job = jobRepo.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException(jobId));

        if (updateJobDTO.getName() != null) {
            job.setName(updateJobDTO.getName());
        }

        if (updateJobDTO.getStartDate() != null) {
            job.setStartDate(updateJobDTO.getStartDate());
        }
        if (updateJobDTO.getEndDate() != null) {
            job.setEndDate(updateJobDTO.getEndDate());
        }

        if (updateJobDTO.getTempId() != null) {
            Temp temp = tempRepo.findById(updateJobDTO.getTempId())
                    .orElseThrow(() -> new TempNotFoundException(updateJobDTO.getTempId()));

            boolean hasConflict = temp.getJobs().stream()
                    .anyMatch(existingJob -> {
                        LocalDate existingStart = existingJob.getStartDate();
                        LocalDate existingEnd = existingJob.getEndDate();
                        LocalDate newStart = updateJobDTO.getStartDate();
                        LocalDate newEnd = updateJobDTO.getEndDate();

                        return (newStart != null && newEnd != null) &&
                                ((newStart.isBefore(existingEnd) && newEnd.isAfter(existingStart)) ||
                                        (newStart.isEqual(existingStart) || newEnd.isEqual(existingEnd)) ||
                                        (newStart.isAfter(existingStart) && newStart.isBefore(existingEnd)) ||
                                        (newEnd.isAfter(existingStart) && newEnd.isBefore(existingEnd)));
                    });

            if (hasConflict) {
                throw new JobDateConflictException(temp.getId(), jobId);
            }

            job.setTemp(temp);
        }

        return jobRepo.save(job);
    }

    public List<Job> getJobsByTempAssigned(boolean assigned) {
        if (assigned) {
            return jobRepo.findByTempIsNotNull();
        } else {
            return jobRepo.findByTempIsNull();
        }
    }

}
