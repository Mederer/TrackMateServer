package com.mitchelldederer.trackmateserver.jobs;

public record UpdateJobRequest(
        int jobId,
        String jobName,
        String jobDescription,
        JobStatus jobStatus

) {
}
