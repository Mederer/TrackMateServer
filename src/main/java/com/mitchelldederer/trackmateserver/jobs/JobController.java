package com.mitchelldederer.trackmateserver.jobs;

import com.mitchelldederer.trackmateserver.exceptions.AppEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("jobs")
    public ResponseEntity<JobDTO> createJobTest(@RequestBody CreateJobRequest jobRequest) {
        JobDTO newJobDto = jobService.createJob(jobRequest);
        return new ResponseEntity<>(newJobDto, HttpStatus.CREATED);
    }

    @GetMapping("jobs")
    public ResponseEntity<List<JobDTO>> getJobs(@RequestParam(required = false) JobStatus status) {
        return new ResponseEntity<>(jobService.getJobs(Optional.ofNullable(status)), HttpStatus.OK);
    }

    @GetMapping("jobs/{id}")
    public ResponseEntity<JobDTO> getJob(@PathVariable int id) {
        return new ResponseEntity<>(jobService.getJob(id), HttpStatus.OK);
    }

    @PutMapping("jobs")
    public ResponseEntity<JobDTO> updateJob(@RequestBody JobDTO job) {
        return new ResponseEntity<>(jobService.updateJob(job), HttpStatus.OK);
    }

    @DeleteMapping("jobs/{jobId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJob(@PathVariable int jobId) {
        jobService.deleteJob(jobId);
    }

    @PostMapping("jobs/{jobId}/categories/{categoryId}")
    public ResponseEntity<JobDTO> addCategoryToJob(@PathVariable int jobId, @PathVariable int categoryId) {
        JobDTO jobDTO = jobService.addCategoryToJob(jobId, categoryId);

        return new ResponseEntity<>(jobDTO, HttpStatus.OK);
    }

    @DeleteMapping("jobs/{jobId}/categories/{categoryId}")
    public ResponseEntity<JobDTO> removeCategoryFromJob(@PathVariable int jobId, @PathVariable int categoryId) {
        return new ResponseEntity<>(jobService.removeCategoryFromJob(jobId, categoryId), HttpStatus.OK);
    }

    @PostMapping("jobs/{jobId}/address/{addressId}")
    public ResponseEntity<JobDTO> setJobAddress(@PathVariable int jobId, @PathVariable int addressId) {
        return new ResponseEntity<>(jobService.setJobAddress(jobId, addressId), HttpStatus.OK);
    }


}
