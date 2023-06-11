package com.mitchelldederer.trackmateserver.jobs;

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
    public ResponseEntity<JobDTO> getJob(@PathVariable int id) {
        Optional<JobDTO> optJob = jobService.getJob(id);

        if (optJob.isEmpty()) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        } else {
            return new ResponseEntity<>(optJob.get(), HttpStatusCode.valueOf(200));
        }
    }

    @PostMapping("jobs")
    public ResponseEntity<JobDTO> createJob(@RequestBody JobDTO newJob) {
        System.out.println("Recieved new job: " + newJob);
        jobService.createJob(newJob);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("jobs/{jobId}/categories/{categoryId}")
    public ResponseEntity<JobDTO> addCategoryToJob(@PathVariable int jobId, @PathVariable int categoryId) {
        Optional<JobDTO> optionalJobDTO = jobService.addCategoryToJob(jobId, categoryId);

        if (optionalJobDTO.isPresent()) {
            return new ResponseEntity<>(optionalJobDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("jobs/{jobId}/update-status/{status}")
    public ResponseEntity<JobDTO> updateStatus(@PathVariable int jobId, @PathVariable JobStatus status) {
        Optional<JobDTO> optionalJobDTO = jobService.updateStatus(jobId, status);

        if (optionalJobDTO.isPresent()) {
            return new ResponseEntity<>(optionalJobDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("jobs/{jobId}/categories/{categoryId}")
    public ResponseEntity<JobDTO> removeCategoryFromJob(@PathVariable int jobId, @PathVariable int categoryId) {
        Optional<JobDTO> optionalJobDTO = jobService.removeCategoryFromJob(jobId, categoryId);

        return optionalJobDTO.map(jobDTO -> new ResponseEntity<>(jobDTO, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
