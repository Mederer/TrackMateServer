package com.mitchelldederer.trackmateserver.jobs;

import com.mitchelldederer.trackmateserver.exceptions.AppEntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @GetMapping("jobs")
    public ResponseEntity<List<JobDTO>> getJobs(@RequestParam(required = false) JobStatus status) {
        System.out.println("Showing jobs for status: " + status);
        return new ResponseEntity<>(jobService.getJobs(status), HttpStatus.OK);
    }

    @GetMapping("jobs/{id}")
    public ResponseEntity<JobDTO> getJob(@PathVariable int id) throws AppEntityNotFoundException {
        return new ResponseEntity<>(jobService.getJob(id), HttpStatus.OK);
    }

    @PostMapping("jobs")
    public ResponseEntity<JobDTO> createJob(@RequestBody JobDTO newJob) {
        jobService.createJob(newJob);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("jobs/{jobId}/categories/{categoryId}")
    public ResponseEntity<JobDTO> addCategoryToJob(@PathVariable int jobId, @PathVariable int categoryId) {
        JobDTO jobDTO = jobService.addCategoryToJob(jobId, categoryId);

        return new ResponseEntity<>(jobDTO, HttpStatus.OK);
    }

    @PutMapping("jobs")
    public ResponseEntity<JobDTO> updateJob(@RequestBody JobDTO job) {
        return new ResponseEntity<>(jobService.updateJob(job), HttpStatus.OK);

    }

    @DeleteMapping("jobs/{jobId}/categories/{categoryId}")
    public ResponseEntity<JobDTO> removeCategoryFromJob(@PathVariable int jobId, @PathVariable int categoryId) {
        return new ResponseEntity<>(jobService.removeCategoryFromJob(jobId, categoryId), HttpStatus.OK);
    }

    @DeleteMapping("jobs/{jobId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJob(@PathVariable int jobId) {
        jobService.deleteJob(jobId);
    }
}
