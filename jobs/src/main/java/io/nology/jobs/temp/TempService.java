package io.nology.jobs.temp;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nology.jobs.exceptions.JobNotFoundException;
import io.nology.jobs.exceptions.TempNotFoundException;
import io.nology.jobs.job.Job;
import io.nology.jobs.job.JobRepository;

@Service
public class TempService {

    @Autowired
    private TempRepository tempRepo;

    @Autowired
    private JobRepository jobRepo;

    public List<Temp> findAll() {
        return this.tempRepo.findAll();
    }

    public Temp createTemp(CreateTempDTO data) {
        Temp temp = new Temp();
        temp.setFirstName(data.getFirstName());
        temp.setLastName(data.getLastName());

        return tempRepo.save(temp);
    }

    public Temp getTempById(Long tempId) {
        return tempRepo.findById(tempId).orElseThrow(() -> new TempNotFoundException(tempId));
    }

    public List<Temp> findAvailableTempsForJob(Long jobId) {
        Job job = jobRepo.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException(jobId));

        List<Temp> allTemps = tempRepo.findAll();

        // filter temps that do not have jobs overlapping
        return allTemps.stream()
                .filter(temp -> temp.getJobs().stream()
                        .noneMatch(existingJob -> (job.getStartDate().isBefore(existingJob.getEndDate())
                                && job.getEndDate().isAfter(existingJob.getStartDate()))))
                .collect(Collectors.toList());
    }
}
